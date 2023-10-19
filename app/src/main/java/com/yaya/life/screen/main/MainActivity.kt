package com.yaya.life.screen.main

import android.content.res.Configuration
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yaya.life.R
import com.yaya.life.common.AppActivity
import com.yaya.life.databinding.ActivityMainBinding
import com.yaya.life.screen.fragments.bottomsheets.SettingsBottomSheetFragment
import com.yaya.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity<String, MainViewModel>() {

    private lateinit var binding: ActivityMainBinding

    override fun initVariables() {
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
