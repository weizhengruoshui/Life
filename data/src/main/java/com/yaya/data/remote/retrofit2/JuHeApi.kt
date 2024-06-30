package com.yaya.data.remote.retrofit2

import androidx.annotation.NonNull
import com.yaya.data.DataWithObject
import com.yaya.data.news.NewsResult
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface JuHeApi {

    /**
     * @param type 类型:top(头条，默认),shehui(社会),guonei(国内),guoji(国际),yule(娱乐),
     * tiyu(体育)junshi(军事),keji(科技),caijing(财经),shishang(时尚)
     */
    @GET("toutiao/index")
    fun getNews(
        @Query("key") key: String,
        @Query("type") type: String
    ): Single<Response<DataWithObject<NewsResult>>>
}
