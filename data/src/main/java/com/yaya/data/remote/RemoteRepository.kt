package com.yaya.data.remote

import android.annotation.SuppressLint
import com.yaya.data.DataRepository
import com.yaya.data.DataWithObject
import com.yaya.data.Repository
import com.yaya.data.local.CombinationDatabase
import com.yaya.data.local.news.NewsEntity
import com.yaya.data.news.NewsResult
import com.yaya.data.remote.retrofit2.JuHeApi
import com.yaya.data.remote.retrofit2.RetrofitClient
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RemoteRepository(
    retrofitClient: RetrofitClient,
    private val combinationDatabase: CombinationDatabase
) : Repository {
    companion object {
        private const val NEWS_API_KEY = "7442a27c37192ead048bcefe13e19c84"
    }

    private val juHeApi: JuHeApi = retrofitClient.generateJuApiService(JuHeApi::class.java)

    override fun getNews(@DataRepository.NewsType type: String): Single<DataWithObject<NewsResult>> {
        return juHeApi.getNews(NEWS_API_KEY, type)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    cacheNews(response, type)
                }
            }.map { response: Response<DataWithObject<NewsResult>> ->
                return@map if (response.isSuccessful) response.body() else null
            }
    }

    private fun <T> doOnResponseSuccessful(response: Response<T>, responseSuccess: () -> Unit) {
        if (response.isSuccessful) {
            responseSuccess.invoke()
        }
    }

    @SuppressLint("CheckResult")
    private fun cacheNews(
        response: Response<DataWithObject<NewsResult>>,
        type: String
    ) {
        val news: MutableList<NewsEntity> = mutableListOf()
        response.body()?.result?.data?.forEach { newsDetail ->
            news.add(
                NewsEntity(
                    newsDetail.uniquekey,
                    System.currentTimeMillis(),
                    type,
                    newsDetail
                )
            )
        }

        combinationDatabase.newsDao()
            .insertAll(news)
            .subscribe({}) {
            }
    }
}