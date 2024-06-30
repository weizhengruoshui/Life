package com.yaya.data.local

import com.yaya.data.DataWithObject
import com.yaya.data.Repository
import com.yaya.data.news.NewsDetail
import com.yaya.data.news.NewsResult
import io.reactivex.Single

class LocalRepository(private val combinationDatabase: CombinationDatabase) : Repository {

    companion object {
        private const val REASON_DATABASE = "database"
        private const val STAT_OK = "ok"
        private const val SUCCESS_CODE = 0
    }

    override fun getNews(type: String): Single<DataWithObject<NewsResult>> {
        return combinationDatabase
            .newsDao()
            .getNews()
            .map { newsEntityList ->
                val dataList: MutableList<NewsDetail> = mutableListOf()
                newsEntityList.forEach { newsEntity ->
                    dataList.add(newsEntity.newsDetail)
                }
                DataWithObject(SUCCESS_CODE, REASON_DATABASE, NewsResult(STAT_OK, dataList))
            }
    }
}
