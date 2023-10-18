package com.stephen.combination.hilt

import com.stephen.combination.common.AppListFragment
import com.stephen.combination.common.list.adapter.RecyclerViewAdapter
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