package com.yaya.data.remote.retrofit2

import androidx.annotation.NonNull
import com.yaya.data.DataWithList
import com.yaya.data.DataWithObject
import com.yaya.data.almanac.AlmanacDay
import com.yaya.data.almanac.AlmanacHour
import com.yaya.data.jokes.JokeDetail
import com.yaya.data.jokes.JokesResult
import com.yaya.data.news.NewsResult
import com.yaya.data.oneiromancy.OneiromancyDetail
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JuHeApi {

    /**
     * @param sort 类型，desc:指定时间之前发布的，asc:指定时间之后发布的
     * @param time 时间戳（10位），如：1418816972
     * @param key 在个人中心->我的数据,接口名称上方查看
     * @param page 当前页数,默认1,最大20
     * @param pageSize 每次返回条数,默认1,最大20
     */
    @GET("joke/content/list.php")
    fun getJokeList(
        @NonNull @Query("sort") sort: String,
        @NonNull @Query("key") key: String,
        @NonNull @Query("time") time: String,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Single<Response<DataWithObject<JokesResult>>>

    /**
     * @param key 在个人中心->我的数据,接口名称上方查看
     * @param page 当前页数,默认1,最大20
     * @param pageSize 每次返回条数,默认1,最大20
     */
    @GET("joke/content/text.php")
    fun getJokeLatest(
        @NonNull @Query("key") key: String,
        @Query("page") page: Int,
        @Query("pagesize") pageSize: Int
    ): Single<Response<DataWithObject<JokesResult>>>

    /**
     * @param key 在个人中心->我的数据,接口名称上方查看
     */
    @GET("joke/randJoke.php")
    fun getJokeRandom(@NonNull @Query("key") key: String): Single<Response<DataWithList<JokeDetail>>>


    /**
     * @param type 类型:top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),
     * tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    @GET("toutiao/index")
    fun getNews(
        @NonNull @Query("key") key: String,
        @Query("type") type: String
    ): Single<Response<DataWithObject<NewsResult>>>


    /**
     * @param date format 2014-09-09
     */
    @GET("laohuangli/d")
    fun getAlmanacDay(
        @Query("key") key: String,
        @Query("date") date: String
    ): Single<Response<DataWithObject<AlmanacDay>>>

    @GET("laohuangli/h")
    fun getAlmanacHours(
        @Query("key") key: String,
        @Query("date") date: String
    ): Single<Response<DataWithList<AlmanacHour>>>


    /**
     * @param q The keywords of the dream
     * @param cid The specific genre, default value is all
     * @param full Whether to show the full information. Could be 1(yes) or 0(no)
     */
    @GET("dream/query")
    fun queryDreams(
        @Query("key") key: String,
        @Query("q") q: String,
        @Query("cid") cid: Int?,
        @Query("full") full: Int = 1
    ): Single<Response<DataWithList<OneiromancyDetail>>>

}