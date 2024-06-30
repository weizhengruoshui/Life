package com.yaya.life.hilt

import com.yaya.life.screen.list.adapter.RecyclerViewAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class RecyclerViewAdapterModule {

    @FragmentScoped
    @Provides
    fun provideDelegationAdapter(): RecyclerViewAdapter {
        return RecyclerViewAdapter()
    }
}