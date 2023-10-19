package com.yaya.life.screen.fragments.news

import com.yaya.life.common.viewmodel.ListViewModel
import com.yaya.data.DataRepository
import com.yaya.data.DataWithObject
import com.yaya.data.news.NewsResult
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.yaya.utils.LogUtils

class NewsViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewTextAndImageItem>>(dataRepository) {

    override fun loadData() {
        compositeDisposable.add(
            dataRepository.getNews()
                .subscribe(::handleNewsData)
        )
    }

    private fun handleNewsData(newsData: DataWithObject<NewsResult>) {
        data.value = newsData.result.data.map {
            RecyclerViewTextAndImageItem(
                it.title,
                "Stephen",
                it.thumbnail_pic_s,
                it.author_name,
                it.date,
                it.url
            )
        }.toMutableList()
    }

    override fun onLoadMore() {
        LogUtils.logD(javaClass.simpleName, "onLoadMore")
    }

    override fun initializeLoadedData(): MutableList<RecyclerViewTextAndImageItem> {
        return mutableListOf()
    }
}