package com.stephen.combination.dagger.module

import com.stephen.combination.dagger.scope.ViewHolderScope
import com.stephen.combination.common.list.holder.viewmodel.ViewModelText
import com.stephen.combination.common.list.holder.viewmodel.ViewModelTextAndImage
import com.stephen.combination.common.list.holder.viewmodel.ViewModelVideo
import com.stephen.player.PlayerViewAffinity
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule {

    @ViewHolderScope
    @Provides
    fun provideViewModelImage(): ViewModelTextAndImage {
        return ViewModelTextAndImage()
    }

    @ViewHolderScope
    @Provides
    fun provideViewModelText(): ViewModelText {
        return ViewModelText()
    }

    @ViewHolderScope
    @Provides
    fun provideViewModelVideo(playerViewAffinity: PlayerViewAffinity): ViewModelVideo {
        return ViewModelVideo(playerViewAffinity)
    }
}