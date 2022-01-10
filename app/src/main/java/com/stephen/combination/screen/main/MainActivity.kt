package com.stephen.combination.screen.main

import android.content.res.Configuration
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.R
import com.stephen.combination.common.AppActivity
import com.stephen.combination.common.manager.fragment.FragmentNavigator
import com.stephen.combination.common.manager.fragment.FragmentToken
import com.stephen.combination.dagger.DaggerName
import com.stephen.combination.databinding.ActivityMainBinding
import com.stephen.combination.screen.fragments.bottomsheets.SettingsBottomSheetFragment
import com.yaya.utils.LogUtils
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppActivity<String, MainViewModel>() {

    @Inject
    @Named(DaggerName.ACTIVITY)
    lateinit var fragmentNavigator: FragmentNavigator

    private val videoListFragmentToken = FragmentToken(FragmentNavigator.FragmentTag.VIDEO_LIST)

    private val newsFragmentToken = FragmentToken(FragmentNavigator.FragmentTag.NEWS)

    private val jokeFragmentToken = FragmentToken(FragmentNavigator.FragmentTag.JOKE)

    private val almanacFragmentToken = FragmentToken(FragmentNavigator.FragmentTag.ALMANAC)

    private lateinit var binding: ActivityMainBinding

    override fun initVariables() {
        activityComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun attributeViews() {
        setContentView(binding.root)
        attributeBottomMenu()
        attributeTopMenu()
        addFragments()
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (newConfig.orientation) {
            Configuration.ORIENTATION_LANDSCAPE -> LogUtils.logD(
                javaClass.simpleName,
                "landScape"
            )
            Configuration.ORIENTATION_PORTRAIT -> LogUtils.logD(
                javaClass.simpleName,
                "portrait"
            )
            else -> LogUtils.logD(
                javaClass.simpleName,
                newConfig.toString()
            )
        }
        LogUtils.logD(javaClass.simpleName, "onConfigurationChanged")
    }

    override fun populateData(data: String) {
        switchMainPage(FragmentToken(data))
    }

    private fun attributeBottomMenu() {
        binding.mainBottomMenu.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.bottom_videos -> switchMainPage(videoListFragmentToken)
                R.id.bottom_news -> switchMainPage(newsFragmentToken)
                R.id.bottom_joke -> switchMainPage(jokeFragmentToken)
                R.id.bottom_almanac -> switchMainPage(almanacFragmentToken)
            }
            return@setOnNavigationItemSelectedListener true
        }

        binding.mainBottomMenu.setOnNavigationItemReselectedListener {
            fragmentNavigator.scrollCurrentFragmentToTop(R.id.main_container)
        }

    }

    private fun attributeTopMenu() {
        binding.mainToolBarSettings.setOnClickListener {
            SettingsBottomSheetFragment.newInstance().show(
                supportFragmentManager,
                FragmentNavigator.FragmentTag.SETTINGS_BOTTOM_DIALOG_FRAGMENT
            )
        }
    }

    private fun addFragments() {
        fragmentNavigator.add(R.id.main_container, videoListFragmentToken)

        fragmentNavigator.add(R.id.main_container, almanacFragmentToken)

        fragmentNavigator.add(R.id.main_container, jokeFragmentToken)

        fragmentNavigator.add(R.id.main_container, newsFragmentToken)
    }


    private fun switchMainPage(fragmentToken: FragmentToken) {
        fragmentNavigator.show(R.id.main_container, fragmentToken)
    }
}
