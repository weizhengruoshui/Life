package com.yaya.data.database

import com.yaya.data.DataWithList
import com.yaya.data.DataWithObject
import com.yaya.data.Repository
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.yaya.data.database.almanac.AlmanacEntity
import com.yaya.data.jokes.JokeDetail
import com.yaya.data.jokes.JokesResult
import com.yaya.data.news.NewsDetail
import com.yaya.data.news.NewsResult
import com.yaya.data.oneiromancy.OneiromancyDetail
import com.yaya.data.viewholder.RecyclerViewVideoItem
import io.reactivex.Single
import retrofit2.Response

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

    override fun getJokes(
        time: String,
        page: Int,
        pageSize: Int,
        sort: String
    ): Single<Response<DataWithObject<JokesResult>>> {
        return getDatabaseJokes()
    }

    override fun getLatestJokes(
        page: Int,
        pageSize: Int
    ): Single<Response<DataWithObject<JokesResult>>> {
        return getDatabaseJokes()
    }

    override fun getRandomJokes(): Single<DataWithList<JokeDetail>> {
        return getDatabaseRandomJokes()
    }

    override fun getAlmanacDay(date: String): Single<Response<DataWithObject<AlmanacDay>>> {
        return getDatabaseAlmanacDay(date)
    }

    override fun getAlmanacHours(date: String): Single<Response<DataWithList<AlmanacHour>>> {
        return getDatabaseAlmanacHours(date)
    }

    override fun queryDream(keywords: String): Single<Response<DataWithList<OneiromancyDetail>>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getVideos(): Single<DataWithList<RecyclerViewVideoItem>> {
        TODO("Not yet implemented")
    }

    private fun getDatabaseAlmanacDay(date: String): Single<Response<DataWithObject<AlmanacDay>>> {
        return getAlmanacInfo(date)
            .map { almanacEntity ->
                Response.success(
                    DataWithObject(
                        SUCCESS_CODE,
                        REASON_DATABASE,
                        almanacEntity.almanacDay
                    )
                )
            }
    }

    private fun getDatabaseAlmanacHours(date: String): Single<Response<DataWithList<AlmanacHour>>> {
        return getAlmanacInfo(date)
            .map { almanacEntity ->
                Response.success(
                    DataWithList(
                        SUCCESS_CODE,
                        REASON_DATABASE,
                        almanacEntity.almanacHours.toMutableList()
                    )
                )
            }
    }

    private fun getAlmanacInfo(date: String): Single<AlmanacEntity> {
        return combinationDatabase
            .almanacDao()
            .getAlmanacInfo(date)
    }

    private fun getDatabaseJokes(): Single<Response<DataWithObject<JokesResult>>> {
        return combinationDatabase
            .jokesDao()
            .getJokes()
            .map { jokeEntities ->
                val dataList: MutableList<JokeDetail> = mutableListOf()
                jokeEntities.forEach { jokeEntity ->
                    dataList.add(jokeEntity.jokeDetail)
                }
                val dataWithObject: DataWithObject<JokesResult> =
                    DataWithObject(SUCCESS_CODE, REASON_DATABASE, JokesResult(dataList))
                Response.success(dataWithObject)
            }
    }

    private fun getDatabaseRandomJokes(): Single<DataWithList<JokeDetail>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}