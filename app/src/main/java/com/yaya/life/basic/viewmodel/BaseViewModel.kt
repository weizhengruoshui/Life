package com.yaya.life.basic.viewmodel

import androidx.lifecycle.ViewModel
import com.yaya.utils.LogUtils
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    open val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun clearDisposables() {
        compositeDisposable.clear()
    }

    fun disposeAllDisposables() {
        compositeDisposable.dispose()
    }

    fun handleException(throwable: Throwable) {
        LogUtils.logE(javaClass.simpleName, throwable.toString())
    }

}