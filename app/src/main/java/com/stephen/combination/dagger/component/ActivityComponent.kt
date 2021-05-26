package com.stephen.combination.dagger.component

import com.stephen.combination.dagger.DaggerName
import com.stephen.combination.dagger.module.ActivityModule
import com.stephen.combination.dagger.scope.ActivityScope
import com.stephen.combination.common.manager.fragment.FragmentNavigator
import com.stephen.combination.screen.account.AccountActivity
import com.yaya.data.DataRepository
import com.stephen.combination.screen.main.MainActivity
import com.stephen.player.PlayerViewAffinity
import dagger.Component
import javax.inject.Named

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(accountActivity: AccountActivity)

    @Named(DaggerName.ACTIVITY)
    fun getFragmentNavigator(): FragmentNavigator

    fun getDataRepository(): DataRepository

    fun getPlayerViewAffinity(): PlayerViewAffinity
}