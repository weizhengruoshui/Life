package com.stephen.combination.dagger.component

import com.stephen.combination.dagger.module.FragmentModule
import com.stephen.combination.dagger.module.NotificationModule
import com.stephen.combination.dagger.scope.FragmentScope
import com.stephen.combination.screen.bottomsheets.SettingsBottomSheetFragment
import com.stephen.combination.screen.fragments.almanac.AlmanacFragment
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [FragmentModule::class, NotificationModule::class]
)
interface FragmentComponent {
    fun inject(almanacFragment: AlmanacFragment)

    fun inject(settingsBottomSheetBinding: SettingsBottomSheetFragment)
}