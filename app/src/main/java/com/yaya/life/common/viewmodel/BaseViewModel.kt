package com.yaya.life.common.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.yaya.utils.LogUtils
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T> : ViewModel() {
    open val compositeDisposable: CompositeDisposable = CompositeDisposable()
    val data: MutableLiveData<T> = MutableLiveData()

    fun clearDisposables() {
        compositeDisposable.clear()
    }

    fun disposeAllDisposables() {
        compositeDisposable.dispose()
    }

    fun handleException(throwable: Throwable) {
        LogUtils.logE(javaClass.simpleName, throwable.toString())
    }

    abstract fun loadData()
}