package com.yaya.data.remote

import android.annotation.SuppressLint
import android.net.Uri
import com.yaya.data.DataRepository
import com.yaya.data.DataWithList
import com.yaya.data.DataWithObject
import com.yaya.data.Repository
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.yaya.data.database.CombinationDatabase
import com.yaya.data.database.jokes.JokeEntity
import com.yaya.data.database.news.NewsEntity
import com.yaya.data.jokes.JokeDetail
import com.yaya.data.jokes.JokesResult
import com.yaya.data.news.NewsResult
import com.yaya.data.oneiromancy.OneiromancyDetail
import com.yaya.data.remote.retrofit2.JuHeApi
import com.yaya.data.remote.retrofit2.RetrofitClient
import com.yaya.data.viewholder.RecyclerViewVideoItem
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class RemoteRepository(
    retrofitClient: RetrofitClient,
    private val combinationDatabase: CombinationDatabase
) : Repository {
    companion object {
        private const val JOKE_API_KEY = "95aae817eb334c0aec2f12f1a78e4f78"
        private const val NEWS_API_KEY = "7442a27c37192ead048bcefe13e19c84"
        private const val ALMANAC_API_KEY = "ba4ed9698d02afc22397202645213715"
        private const val OREIROMANCY_API_KEY = "f07e0b0ff42a32edbf1a42ed6a05841b"
        private const val REASON = "remote repository"
        private const val STAT_OK = "ok"
        private const val SUCCESS_CODE = 200
    }

    private val juHeApi: JuHeApi = retrofitClient.generateJuApiService(JuHeApi::class.java)

    override fun getJokes(
        time: String,
        page: Int,
        pageSize: Int,
        sort: String
    ): Single<Response<DataWithObject<JokesResult>>> {
        return juHeApi.getJokeList(
            sort,
            JOKE_API_KEY, time, page, pageSize
        )
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    cacheJokes(response)
                }
            }
    }

    override fun getLatestJokes(
        page: Int,
        pageSize: Int
    ): Single<Response<DataWithObject<JokesResult>>> {
        return juHeApi.getJokeLatest(JOKE_API_KEY, page, pageSize)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    cacheJokes(response)
                }
            }
    }

    override fun getRandomJokes(): Single<DataWithList<JokeDetail>> {
        return juHeApi.getJokeRandom(JOKE_API_KEY)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    cacheRandomJokes(response)
                }
            }
            .map { response: Response<DataWithList<JokeDetail>> ->
                return@map if (response.isSuccessful) response.body() else null
            }
    }

    override fun getAlmanacDay(date: String): Single<Response<DataWithObject<AlmanacDay>>> {
        return juHeApi.getAlmanacDay(ALMANAC_API_KEY, date)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    //todo cache the information
                }
            }
    }

    override fun getAlmanacHours(date: String): Single<Response<DataWithList<AlmanacHour>>> {
        return juHeApi.getAlmanacHours(ALMANAC_API_KEY, date)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    //todo cache the information
                }
            }
    }

    override fun queryDream(keywords: String): Single<Response<DataWithList<OneiromancyDetail>>> {
        return juHeApi.queryDreams(OREIROMANCY_API_KEY, keywords, null)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    //todo cache the information
                }
            }
    }

    override fun getVideos(): Single<DataWithList<RecyclerViewVideoItem>> {
        return Single.create { emitter: SingleEmitter<Response<DataWithList<RecyclerViewVideoItem>>> ->
            val videos = ArrayList<RecyclerViewVideoItem>()
            val mediaMp3 = RecyclerViewVideoItem(
                Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"),
                "Jazz In Paris.mp3",
                "mp3"
            )
            val mediaMp4 = RecyclerViewVideoItem(
                Uri.parse("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"),
                "Big Buck Bunny(320*180).mp4",
                "mp4"
            )
            val mediaDash = RecyclerViewVideoItem(
                Uri.parse("https://www.youtube.com/api/manifest/dash/id/bf5bb2419360daf1/source/youtube?as=fmp4_audio_clear,fmp4_sd_hd_clear&sparams=ip,ipbits,expire,source,id,as&ip=0.0.0.0&ipbits=0&expire=19000000000&signature=51AF5F39AB0CEC3E5497CD9C900EBFEAECCCB5C7.8506521BFC350652163895D4C26DEE124209AA9E&key=ik0"),
                "Youtube.dash",
                "dash"
            )
            videos.add(mediaMp4)
            videos.add(mediaMp3)
            videos.add(mediaDash)
            emitter.onSuccess(
                Response.success(
                    DataWithList(
                        SUCCESS_CODE,
                        REASON,
                        videos
                    )
                )
            )
        }.subscribeOn(Schedulers.io())
            .doOnSuccess { response ->
                doOnResponseSuccessful(response) {
                    //todo cache the information
                }
            }
            .map { response: Response<DataWithList<RecyclerViewVideoItem>> ->
                return@map if (response.isSuccessful) response.body() else null
            }
    }

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
    private fun cacheRandomJokes(response: Response<DataWithList<JokeDetail>>) {
        val jokes: MutableList<JokeEntity> = mutableListOf()
        response.body()?.result?.forEach {
            jokes.add(JokeEntity(it.hashId, it))
        }

        combinationDatabase.jokesDao()
            .insertAll(jokes)
            .subscribe({}) {
            }
    }

    @SuppressLint("CheckResult")
    private fun cacheJokes(response: Response<DataWithObject<JokesResult>>) {
        val jokes: MutableList<JokeEntity> = mutableListOf()
        response.body()?.result?.data?.forEach { jokeDetail ->
            jokes.add(JokeEntity(jokeDetail.hashId, jokeDetail))
        }

        combinationDatabase.jokesDao()
            .insertAll(jokes)
            .subscribe({}) {
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