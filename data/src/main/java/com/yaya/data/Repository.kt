package com.yaya.data

import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.yaya.data.jokes.JokeDetail
import com.yaya.data.jokes.JokesResult
import com.yaya.data.news.NewsResult
import com.yaya.data.oneiromancy.OneiromancyDetail
import com.yaya.data.viewholder.RecyclerViewVideoItem
import com.yaya.utils.DateUtils
import io.reactivex.Single
import retrofit2.Response

interface Repository {
    fun getNews(@DataRepository.NewsType type: String = DataRepository.TOP): Single<DataWithObject<NewsResult>>

    fun getJokes(
        time: String = "1418816972",
        page: Int = 1,
        pageSize: Int = 20,
        sort: String = DataRepository.DESC_SORT
    ): Single<Response<DataWithObject<JokesResult>>>

    fun getLatestJokes(
        page: Int = 1,
        pageSize: Int = 20
    ): Single<Response<DataWithObject<JokesResult>>>

    fun getRandomJokes(): Single<DataWithList<JokeDetail>>

    fun getAlmanacDay(date: String = DateUtils.getCurrentDate(DataRepository.ALMANAC_DATE_FORMAT)): Single<Response<DataWithObject<AlmanacDay>>>

    fun getAlmanacHours(date: String = DateUtils.getCurrentDate(DataRepository.ALMANAC_DATE_FORMAT)): Single<Response<DataWithList<AlmanacHour>>>

    fun queryDream(keywords: String): Single<Response<DataWithList<OneiromancyDetail>>>

    fun getVideos(): Single<DataWithList<RecyclerViewVideoItem>>
}