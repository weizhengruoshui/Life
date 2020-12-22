package com.stephen.combination.dagger.module

import androidx.fragment.app.Fragment
import com.stephen.combination.dagger.DaggerName
import com.stephen.combination.dagger.scope.FragmentScope
import com.stephen.combination.common.manager.fragment.AppFragmentFactory
import com.stephen.combination.common.manager.fragment.FragmentNavigator
import com.stephen.combination.common.viewmodel.DataViewModelFactory
import com.yaya.data.DataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class FragmentModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideDataRepositoryViewModelFactory(dataRepository: DataRepository): DataViewModelFactory {
        return DataViewModelFactory(dataRepository)
    }

    @FragmentScope
    @Provides
    @Named(DaggerName.FRAGMENT)
    fun provideFragmentNavigator(appFragmentFactory: AppFragmentFactory): FragmentNavigator {
        return FragmentNavigator(fragment.childFragmentManager, appFragmentFactory)
    }
}