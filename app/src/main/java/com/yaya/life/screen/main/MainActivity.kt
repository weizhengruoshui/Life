package com.yaya.life.screen.main

import android.content.res.Configuration
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.yaya.life.R
import com.yaya.life.basic.AppActivity
import com.yaya.life.databinding.ActivityMainBinding
import com.yaya.life.screen.bottomsheets.SettingsBottomSheetFragment
import com.yaya.utils.LogUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        val navController = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment)
            ?.findNavController() ?: return
        setupBottomMenu(navController)
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
