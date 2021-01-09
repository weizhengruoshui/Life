package com.stephen.combination.common.viewmodel

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import com.yaya.data.DataRepository

/**
 * @property T this is the list's item type
 */
abstract class ListViewModel<T : MutableList<out Parcelable>>(protected val dataRepository: DataRepository) :
    BaseViewModel<T>() {

    var page = 1
    var pageSize = 20
    val moreData: MutableLiveData<T> = MutableLiveData()

    private val loadMoreThreshold = 2

    fun loadMore(lastVisibleItemPosition: Int, itemCount: Int) {
        if (lastVisibleItemPosition + loadMoreThreshold > itemCount) {
            page++
            onLoadMore()
        }
    }

    fun resetParams() {
        page = 1
    }

    abstract fun initializeLoadedData(): T

    abstract fun onLoadMore()
}
