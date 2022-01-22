package com.stephen.combination.dagger.component

import com.stephen.combination.dagger.module.ActivityModule
import com.stephen.combination.dagger.scope.ActivityScope
import com.stephen.combination.screen.account.AccountActivity
import com.stephen.combination.screen.main.MainActivity
import com.stephen.player.PlayerViewAffinity
import com.yaya.data.DataRepository
import dagger.Component

@ActivityScope
@Component(
    dependencies = [AppComponent::class],
    modules = [ActivityModule::class]
)
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(accountActivity: AccountActivity)

    fun getDataRepository(): DataRepository

    fun getPlayerViewAffinity(): PlayerViewAffinity
}