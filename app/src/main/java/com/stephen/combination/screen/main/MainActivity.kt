package com.stephen.combination.screen.main

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.stephen.combination.R
import com.stephen.combination.common.AppActivity
import com.stephen.combination.databinding.ActivityMainBinding
import com.stephen.combination.screen.fragments.bottomsheets.SettingsBottomSheetFragment
import com.yaya.utils.LogUtils

class MainActivity : AppActivity<String, MainViewModel>() {

    private lateinit var binding: ActivityMainBinding

    override fun initVariables() {
        activityComponent.inject(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun attributeViews() {
        setContentView(binding.root)

        val navController = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
            ?.findNavController() ?: return
        setupActionBar()
        setupBottomMenu(navController)
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
        //Don't need to load data.
    }

    private fun setupActionBar() {
        binding.mainToolBarSettings.setOnClickListener {
            SettingsBottomSheetFragment.newInstance().show(
                supportFragmentManager,
                SettingsBottomSheetFragment.TAG
            )
        }
    }


    private fun setupBottomMenu(navController: NavController) {
        binding.mainBottomMenu.setupWithNavController(navController)
    }
}
