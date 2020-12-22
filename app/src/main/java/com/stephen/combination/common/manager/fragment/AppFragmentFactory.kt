package com.stephen.combination.common.manager.fragment

import com.stephen.combination.common.BaseFragment
import com.stephen.combination.screen.fragments.almanac.AlmanacFragment
import com.stephen.combination.screen.fragments.jokes.JokesFragmentApp
import com.stephen.combination.screen.fragments.news.NewsFragment
import com.stephen.combination.screen.fragments.video.VideoContentFragment
import com.stephen.combination.screen.fragments.video.list.VideoListFragment
import javax.inject.Inject

class AppFragmentFactory @Inject constructor() {

    fun getFragment(fragmentToken: FragmentToken): BaseFragment<*, *> {
        return when (fragmentToken.tag) {
            FragmentNavigator.FragmentTag.NEWS -> NewsFragment.newInstance()
            FragmentNavigator.FragmentTag.JOKE -> JokesFragmentApp.newInstance()
            FragmentNavigator.FragmentTag.VIDEO_LIST -> VideoListFragment.newInstance()
            FragmentNavigator.FragmentTag.ALMANAC -> AlmanacFragment.newInstance()
            FragmentNavigator.FragmentTag.VIDEO -> VideoContentFragment.newInstance()
            else -> throw Exception("Wrong fragment token id")
        }
    }
}