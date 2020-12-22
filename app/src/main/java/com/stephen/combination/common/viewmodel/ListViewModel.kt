package com.stephen.combination.common.viewmodel

import android.os.Parcelable
import com.yaya.data.DataRepository

/**
 * @property I this is the list's item type
 */
abstract class ListViewModel<I : MutableList<out Parcelable>>(protected val dataRepository: DataRepository) :
    BaseViewModel<I>() {

    abstract fun loadMore()
}