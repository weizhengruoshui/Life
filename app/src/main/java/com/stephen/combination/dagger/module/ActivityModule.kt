package com.stephen.combination.dagger.module

import androidx.fragment.app.FragmentActivity
import com.stephen.combination.dagger.DaggerName
import com.stephen.combination.dagger.scope.ActivityScope
import com.stephen.combination.common.manager.fragment.AppFragmentFactory
import com.stephen.combination.common.manager.fragment.FragmentNavigator
import com.stephen.player.PlayerViewAffinity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class ActivityModule constructor(private val activity: FragmentActivity) {

    @ActivityScope
    @Provides
    @Named(DaggerName.ACTIVITY)
    fun provideFragmentNavigator(appFragmentFactory: AppFragmentFactory): FragmentNavigator {
        return FragmentNavigator(
            activity.supportFragmentManager,
            appFragmentFactory
        )
    }

    @ActivityScope
    @Provides
    fun providePlayerViewAffinity(): PlayerViewAffinity {
        return PlayerViewAffinity(activity)
    }
}