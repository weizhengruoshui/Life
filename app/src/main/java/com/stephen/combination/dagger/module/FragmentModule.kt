package com.stephen.combination.dagger.module

import androidx.fragment.app.Fragment
import com.stephen.combination.common.viewmodel.DataViewModelFactory
import com.stephen.combination.dagger.scope.FragmentScope
import com.yaya.data.DataRepository
import dagger.Module
import dagger.Provides

@Module
class FragmentModule(private val fragment: Fragment) {

    @FragmentScope
    @Provides
    fun provideDataRepositoryViewModelFactory(dataRepository: DataRepository): DataViewModelFactory {
        return DataViewModelFactory(dataRepository)
    }
}