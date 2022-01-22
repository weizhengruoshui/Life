package com.stephen.combination.dagger.module

import androidx.fragment.app.FragmentActivity
import com.stephen.combination.dagger.scope.ActivityScope
import com.stephen.player.PlayerViewAffinity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule constructor(private val activity: FragmentActivity) {

    @ActivityScope
    @Provides
    fun providePlayerViewAffinity(): PlayerViewAffinity {
        return PlayerViewAffinity(activity)
    }
}