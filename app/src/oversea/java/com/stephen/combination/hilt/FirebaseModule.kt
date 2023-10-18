package com.stephen.combination.hilt

import android.app.Application
import com.stephen.combination.firebase.FirebaseAnalyticsAgent
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