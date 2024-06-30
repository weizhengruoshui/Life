package com.yaya.data

import com.yaya.data.news.NewsResult
import io.reactivex.Single

interface Repository {
    fun getNews(@DataRepository.NewsType type: String = DataRepository.TOP): Single<DataWithObject<NewsResult>>
}
