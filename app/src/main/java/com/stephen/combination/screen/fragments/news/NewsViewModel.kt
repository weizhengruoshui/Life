package com.stephen.combination.screen.fragments.news

import com.yaya.data.DataRepository
import com.yaya.data.DataWithObject
import com.yaya.data.news.NewsResult
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.stephen.combination.common.viewmodel.ListViewModel

class NewsViewModel(dataRepository: DataRepository) :
    ListViewModel<MutableList<RecyclerViewTextAndImageItem>>(dataRepository) {

    override fun loadData() {
        compositeDisposable.add(
            dataRepository.getNews()
                .subscribe(::handleNewsData)
        )
    }

    private fun handleNewsData(newsData: DataWithObject<NewsResult>) {
        liveData.value = newsData.result.data.map {
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

    override fun loadMore() {
        TODO("Not yet implemented")
    }
}