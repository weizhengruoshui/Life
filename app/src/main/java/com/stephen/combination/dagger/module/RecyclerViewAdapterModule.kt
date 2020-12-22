package com.stephen.combination.dagger.module

import com.stephen.combination.dagger.scope.FragmentScope
import com.stephen.combination.common.list.adapter.RecyclerViewAdapter
import com.stephen.combination.common.AppListFragment
import dagger.Module
import dagger.Provides

@Module
class RecyclerViewAdapterModule(private val appListFragment: AppListFragment<*, *>) {

    @FragmentScope
    @Provides
    fun provideDelegationAdapter(): RecyclerViewAdapter {
        return RecyclerViewAdapter(appListFragment)
    }
}