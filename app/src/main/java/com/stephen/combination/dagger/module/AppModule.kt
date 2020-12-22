package com.stephen.combination.dagger.module

import android.app.Application
import com.stephen.combination.analytics.AnalyticsAgency
import com.stephen.combination.dagger.scope.AppScope
import com.yaya.data.DataRepository
import com.yaya.data.database.CombinationDatabase
import com.yaya.data.database.LocalRepository
import com.yaya.data.remote.RemoteRepository
import com.yaya.data.remote.retrofit2.RetrofitClient
import com.yaya.utils.ToastUtils
import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @AppScope
    @Provides
    fun provideAnalyticsAgency(): AnalyticsAgency {
        return AnalyticsAgency()
    }

    @AppScope
    @Provides
    fun provideCombinationToast(): ToastUtils {
        return ToastUtils(application)
    }

    @AppScope
    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @AppScope
    @Provides
    fun provideCombinationDatabase(): CombinationDatabase {
        return CombinationDatabase.getInstance(application)
    }

    @AppScope
    @Provides
    fun provideJokeApiAction(
        retrofitClient: RetrofitClient,
        combinationDatabase: CombinationDatabase
    ): RemoteRepository {
        return RemoteRepository(retrofitClient, combinationDatabase)
    }

    @AppScope
    @Provides
    fun provideLocalRepository(combinationDatabase: CombinationDatabase): LocalRepository {
        return LocalRepository(combinationDatabase)
    }

    @AppScope
    @Provides
    fun provideDataRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): DataRepository {
        return DataRepository(remoteRepository, localRepository)
    }
}