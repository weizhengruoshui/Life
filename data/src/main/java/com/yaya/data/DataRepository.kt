package com.yaya.data

import androidx.annotation.StringDef
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.yaya.data.database.LocalRepository
import com.yaya.data.jokes.JokeDetail
import com.yaya.data.jokes.JokesResult
import com.yaya.data.news.NewsResult
import com.yaya.data.oneiromancy.OneiromancyDetail
import com.yaya.data.remote.RemoteRepository
import com.yaya.data.viewholder.RecyclerViewVideoItem
import com.yaya.utils.LogUtils
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import retrofit2.Response

class DataRepository(
    private val remoteRepository: RemoteRepository,
    private val localRepository: LocalRepository
) : Repository {

    companion object {
        const val TOP = "top"
        const val SOCIETY = "shehui"
        const val DOMESTIC = "guonei"
        const val INTERNATIONAL = "guoji"
        const val ENTERTAINMENT = "yule"
        const val SPORTS = "tiyu"
        const val POLITICS = "junshi"
        const val TECHNOLOGY = "keji"
        const val ECONOMY = "caijing"
        const val FASHION = "shishang"
        const val ASC_SORT = "asc"
        const val DESC_SORT = "desc"
        const val ALMANAC_DATE_FORMAT = "yyyy-MM-dd"
    }

    @StringDef(
        TOP,
        SOCIETY,
        DOMESTIC,
        INTERNATIONAL,
        ENTERTAINMENT,
        SPORTS,
        POLITICS,
        TECHNOLOGY,
        ECONOMY,
        FASHION
    )
    annotation class NewsType

    override fun getNews(type: String): Single<DataWithObject<NewsResult>> {
        return remoteRepository.getNews(type)
            .onErrorResumeNext {
                LogUtils.logD(javaClass.simpleName, "RequestNews Error, use the local data.")
                localRepository.getNews(type)
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getJokes(
        time: String,
        page: Int,
        pageSize: Int,
        sort: String
    ): Single<DataWithObject<JokesResult>> {
        return remoteRepository.getJokes()
            .onErrorResumeNext(localRepository.getJokes())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getLatestJokes(
        page: Int,
        pageSize: Int
    ): Single<DataWithObject<JokesResult>> {
        return remoteRepository.getLatestJokes(page, pageSize)
            .onErrorResumeNext(localRepository.getLatestJokes())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getRandomJokes(): Single<DataWithList<JokeDetail>> {
        return remoteRepository.getRandomJokes()
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * @param date the date format is 2014-09-09
     */
    override fun getAlmanacDay(date: String): Single<Response<DataWithObject<AlmanacDay>>> {
        return remoteRepository.getAlmanacDay(date)
            .onErrorResumeNext(localRepository.getAlmanacDay(date))
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAlmanacHours(date: String): Single<Response<DataWithList<AlmanacHour>>> {
        return remoteRepository.getAlmanacHours(date)
            .onErrorResumeNext(localRepository.getAlmanacHours(date))
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun queryDream(keywords: String): Single<Response<DataWithList<OneiromancyDetail>>> {
        return remoteRepository.queryDream(keywords)
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getVideos(): Single<DataWithList<RecyclerViewVideoItem>> {
        return remoteRepository.getVideos()
            .observeOn(AndroidSchedulers.mainThread())
    }
}