package com.stephen.combination.common.manager.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.stephen.combination.common.AppListFragment

class FragmentNavigator constructor(
    private val fragmentManager: FragmentManager,
    private val appFragmentFactory: AppFragmentFactory
) {

    fun push(@IdRes containerViewId: Int, fragmentToken: FragmentToken) {
        if (getTheShownFragment(containerViewId)?.tag == fragmentToken.tag) {
            return
        }

        val fragmentToShow =
            fragmentManager.findFragmentByTag(fragmentToken.tag) ?: appFragmentFactory.getFragment(
                fragmentToken
            )
        fragmentManager.beginTransaction().apply {
            replace(containerViewId, fragmentToShow, fragmentToken.tag)
            if (fragmentToken.isBackStack) {
                addToBackStack(fragmentToken.tag)
            }
            commit()
        }
    }

    fun scrollCurrentFragmentToTop(@IdRes containerViewId: Int) {
        val currentFragment = getTheShownFragment(containerViewId)
        if (currentFragment is AppListFragment<*, *>) {
            currentFragment.scrollToTop()
        }
    }

    fun popBack() {
        fragmentManager.popBackStack()
    }

    fun getTheShownFragment(@IdRes containerViewId: Int): Fragment? {
        return fragmentManager.findFragmentById(containerViewId)
    }

    class FragmentTag {

        companion object {
            // Fragment ID
            const val NEWS = "news list"
            const val JOKE = "joke list"
            const val ALMANAC = "almanac"
            const val VIDEO_LIST = "video list"
            const val VIDEO = "video"

            // DialogFragment ID
            const val SETTINGS_BOTTOM_DIALOG_FRAGMENT = "settings bottom dialog fragment"
        }
    }
}