package com.stephen.combination.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T> : ViewModel() {
    open val compositeDisposable: CompositeDisposable = CompositeDisposable()
    var liveData: MutableLiveData<T> = MutableLiveData()

    fun clearDisposables() {
        compositeDisposable.clear()
    }

    fun disposeAllDisposables() {
        compositeDisposable.dispose()
    }

    fun handleException(throwable: Throwable) {
        com.yaya.utils.LogUtils.logE(javaClass.simpleName, throwable.toString())
    }

    abstract fun loadData()
}