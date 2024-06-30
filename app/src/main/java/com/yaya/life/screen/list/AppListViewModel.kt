package com.yaya.life.screen.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yaya.data.DataRepository
import com.yaya.data.DataWithObject
import com.yaya.data.news.NewsResult
import com.yaya.data.viewholderitems.RecyclerViewTextAndImageItem
import com.yaya.life.basic.viewmodel.BaseViewModel
import com.yaya.utils.LogUtils

class AppListViewModel(private val dataRepository: DataRepository) :
    BaseViewModel() {

    private val _listData: MutableLiveData<List<RecyclerViewTextAndImageItem>> =
        MutableLiveData()

    val listData : LiveData<List<RecyclerViewTextAndImageItem>> = _listData

    private var page = 1
    private val loadMoreThreshold = 2

    fun loadMore(lastVisibleItemPosition: Int, itemCount: Int) {
        if (lastVisibleItemPosition + loadMoreThreshold > itemCount) {
            page++
            LogUtils.logD(javaClass.simpleName, "onLoadMore")
        }
    }

    fun loadData() {
        compositeDisposable.add(
            dataRepository.getNews()
                .subscribe(::handleNewsData)
        )
    }

    private fun handleNewsData(newsData: DataWithObject<NewsResult>) {
        _listData.value = newsData.result.data.map {
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

}
