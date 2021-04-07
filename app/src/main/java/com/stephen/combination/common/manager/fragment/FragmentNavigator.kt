package com.stephen.combination.common.manager.fragment

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.stephen.combination.common.AppListFragment

class FragmentNavigator constructor(
    private val fragmentManager: FragmentManager,
    private val appFragmentFactory: AppFragmentFactory
) {

    private val currentShowFragmentTags = hashMapOf<Int, FragmentToken>()

    fun add(@IdRes containerViewId: Int, fragmentToken: FragmentToken) {
        if (null != fragmentManager.findFragmentByTag(fragmentToken.tag)) {
            return
        }

        val fragment = appFragmentFactory.getFragment(fragmentToken)

        fragmentManager.beginTransaction().apply {
            add(containerViewId, fragment, fragmentToken.tag)
            hide(fragment)
            commit()
        }
    }

    fun show(@IdRes containerViewId: Int, fragmentToken: FragmentToken) {
        if (null == fragmentManager.findFragmentByTag(fragmentToken.tag) || currentShowFragmentTags[containerViewId] == fragmentToken) {
            return
        }

        val currentFragment = fragmentManager.findFragmentByTag(currentShowFragmentTags[containerViewId]?.tag)

        fragmentManager.findFragmentByTag(fragmentToken.tag)?.also { fragment ->
            if (null != currentFragment) {
                fragmentManager.beginTransaction()
                    .hide(currentFragment)
                    .show(fragment)
                    .commit()
            } else {
                fragmentManager.beginTransaction()
                    .show(fragment)
                    .commit()
            }
        }
        currentShowFragmentTags[containerViewId] = fragmentToken
    }


    fun scrollCurrentFragmentToTop(@IdRes containerViewId: Int) {
        val currentFragment = getCurrentFragment(containerViewId)
        if (currentFragment is AppListFragment<*, *>) {
            currentFragment.scrollToTop()
        }
    }

    private fun getCurrentFragment(@IdRes containerViewId: Int): Fragment? {
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