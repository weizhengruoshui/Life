package com.stephen.combination.hilt

import android.content.Context
import androidx.fragment.app.FragmentActivity
import com.stephen.player.PlayerViewAffinity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @ActivityScoped
    @Provides
    fun providePlayerViewAffinity(@ActivityContext context: Context): PlayerViewAffinity {
        return PlayerViewAffinity(context)
    }
}