package com.stephen.combination.hilt

import com.stephen.combination.common.viewmodel.DataViewModelFactory
import com.yaya.data.DataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.scopes.FragmentScoped

@Module
@InstallIn(FragmentComponent::class)
class FragmentModule {

    @FragmentScoped
    @Provides
    fun provideDataRepositoryViewModelFactory(dataRepository: DataRepository): DataViewModelFactory {
        return DataViewModelFactory(dataRepository)
    }
}