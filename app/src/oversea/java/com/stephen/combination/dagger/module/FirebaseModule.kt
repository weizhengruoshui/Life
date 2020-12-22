package com.stephen.combination.dagger.module

import android.app.Application
import com.stephen.combination.dagger.scope.AppScope
import com.stephen.combination.firebase.FirebaseAnalyticsAgent
import dagger.Module
import dagger.Provides

@Module
class FirebaseModule(private var application: Application) {

    @AppScope
    @Provides
    fun provideFirebaseAnalyticsAgent(): FirebaseAnalyticsAgent {
        return FirebaseAnalyticsAgent(application)
    }
}