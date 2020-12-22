package com.stephen.combination.screen.main

import android.content.res.Configuration
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.R
import com.stephen.combination.common.BaseActivity
import com.stephen.combination.common.manager.fragment.FragmentNavigator
import com.stephen.combination.common.manager.fragment.FragmentToken
import com.stephen.combination.dagger.DaggerName
import com.stephen.combination.databinding.ActivityMainBinding
import com.stephen.combination.screen.bottomsheets.SettingsBottomSheetFragment
import com.yaya.utils.LogUtils
import javax.inject.Inject
import javax.inject.Named

class MainActivity : BaseActivity<String, MainViewModel>() {

    @Inject
    @Named(DaggerName.ACTIVITY)
    lateinit var fragmentNavigator: FragmentNavigator

    private lateinit var binding: ActivityMainBinding

    override fun initVariables() {
        activityComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun attributeViews() {
        setContentView(binding.root)
        attributeBottomMenu()
        attributeTopMenu()
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
        }
        LogUtils.logD(javaClass.simpleName, "onConfigurationChanged")
    }

    override fun populateData(data: String) {
        fragmentNavigator.push(
            R.id.main_container,
            FragmentToken(data)
        )
    }

    private fun attributeBottomMenu() {
        binding.mainBottomMenu.setOnNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.bottom_videos -> fragmentNavigator.push(
                    R.id.main_container,
                    FragmentToken(FragmentNavigator.FragmentTag.VIDEO_LIST, true)
                )
                R.id.bottom_news -> fragmentNavigator.push(
                    R.id.main_container,
                    FragmentToken(FragmentNavigator.FragmentTag.NEWS, true)
                )
                R.id.bottom_joke -> fragmentNavigator.push(
                    R.id.main_container,
                    FragmentToken(FragmentNavigator.FragmentTag.JOKE, true)
                )
                R.id.bottom_almanac -> fragmentNavigator.push(
                    R.id.main_container,
                    FragmentToken(FragmentNavigator.FragmentTag.ALMANAC, true)
                )
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
}
