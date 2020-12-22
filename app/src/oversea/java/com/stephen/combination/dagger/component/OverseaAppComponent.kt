package com.stephen.combination.dagger.component

import com.stephen.combination.OverSeaApplication
import com.stephen.combination.dagger.scope.AppScope
import com.stephen.combination.dagger.module.FirebaseModule
import dagger.Component

@AppScope
@Component(modules = [FirebaseModule::class])
interface OverseaAppComponent {
    fun inject(application: OverSeaApplication)
}
