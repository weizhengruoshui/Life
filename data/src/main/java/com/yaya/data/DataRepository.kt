package com.yaya.data

import androidx.annotation.StringDef
import com.yaya.data.local.LocalRepository
import com.yaya.data.news.NewsResult
import com.yaya.data.remote.RemoteRepository
import com.yaya.utils.LogUtils
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers

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
}