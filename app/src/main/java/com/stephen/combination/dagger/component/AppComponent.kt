package com.stephen.combination.dagger.component

import com.stephen.combination.MainApplication
import com.stephen.combination.dagger.module.AppModule
import com.stephen.combination.dagger.scope.AppScope
import com.yaya.data.DataRepository
import com.yaya.data.database.CombinationDatabase
import com.yaya.data.remote.retrofit2.RetrofitClient
import com.yaya.utils.ToastUtils
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(mainApplication: MainApplication)

    fun getRetrofitClient(): RetrofitClient

    fun getCombinationToast(): ToastUtils

    fun getCombinationDatabase(): CombinationDatabase

    fun getDataRepository(): DataRepository
}