package com.stephen.combination.dagger.component

import com.stephen.combination.dagger.module.ViewModelModule
import com.stephen.combination.dagger.scope.ViewHolderScope
import com.stephen.combination.common.list.holder.view.ViewHolderText
import com.stephen.combination.common.list.holder.view.ViewHolderTextAndImage
import com.stephen.combination.common.list.holder.view.ViewHolderVideo
import dagger.Component

@ViewHolderScope
@Component(dependencies = [ListFragmentComponent::class], modules = [ViewModelModule::class])
interface ViewHolderComponent {
    fun inject(viewHolderTextAndImage: ViewHolderTextAndImage)

    fun inject(viewHolderText: ViewHolderText)

    fun inject(viewHolderVideo: ViewHolderVideo)
}