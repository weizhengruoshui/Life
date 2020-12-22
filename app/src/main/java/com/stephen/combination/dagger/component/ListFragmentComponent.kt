package com.stephen.combination.dagger.component

import com.stephen.combination.dagger.module.FragmentModule
import com.stephen.combination.dagger.module.RecyclerViewAdapterModule
import com.stephen.combination.dagger.scope.FragmentScope
import com.stephen.combination.screen.fragments.jokes.JokesFragmentApp
import com.stephen.combination.screen.fragments.news.NewsFragment
import com.stephen.combination.screen.fragments.video.VideoContentFragment
import com.stephen.combination.screen.fragments.video.list.VideoListFragment
import com.stephen.player.PlayerViewAffinity
import dagger.Component

@FragmentScope
@Component(
    dependencies = [ActivityComponent::class],
    modules = [FragmentModule::class,
        RecyclerViewAdapterModule::class]
)
interface ListFragmentComponent {
    fun inject(jokesFragment: JokesFragmentApp)

    fun inject(newsFragment: NewsFragment)

    fun inject(videoListFragment: VideoListFragment)

    fun inject(videoContentFragment: VideoContentFragment)

    fun getPlayerViewAffinity(): PlayerViewAffinity
}