package com.yaya.life.hilt

import android.app.Application
import com.yaya.life.firebase.FirebaseAnalyticsAgent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FirebaseModule() {

    @Singleton
    @Provides
    fun provideFirebaseAnalyticsAgent(application: Application): FirebaseAnalyticsAgent {
        return FirebaseAnalyticsAgent(application)
    }
}