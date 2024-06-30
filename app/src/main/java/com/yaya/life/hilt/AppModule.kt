package com.yaya.life.hilt

import android.app.Application
import android.content.Context
import com.yaya.life.analytics.AnalyticsAgency
import com.yaya.data.DataRepository
import com.yaya.data.local.CombinationDatabase
import com.yaya.data.local.LocalRepository
import com.yaya.data.remote.RemoteRepository
import com.yaya.data.remote.retrofit2.RetrofitClient
import com.yaya.utils.ToastUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideAnalyticsAgency(): AnalyticsAgency {
        return AnalyticsAgency()
    }

    @Singleton
    @Provides
    fun provideCombinationToast(@ApplicationContext application: Context): ToastUtils {
        return ToastUtils(application)
    }

    @Singleton
    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient()
    }

    @Singleton
    @Provides
    fun provideCombinationDatabase(application: Application): CombinationDatabase {
        return CombinationDatabase.getInstance(application)
    }

    @Singleton
    @Provides
    fun provideJokeApiAction(
        retrofitClient: RetrofitClient,
        combinationDatabase: CombinationDatabase
    ): RemoteRepository {
        return RemoteRepository(retrofitClient, combinationDatabase)
    }

    @Singleton
    @Provides
    fun provideLocalRepository(combinationDatabase: CombinationDatabase): LocalRepository {
        return LocalRepository(combinationDatabase)
    }

    @Singleton
    @Provides
    fun provideDataRepository(
        remoteRepository: RemoteRepository,
        localRepository: LocalRepository
    ): DataRepository {
        return DataRepository(remoteRepository, localRepository)
    }
}