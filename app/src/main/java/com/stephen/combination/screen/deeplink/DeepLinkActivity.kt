package com.stephen.combination.screen.deeplink

import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.R
import com.stephen.combination.common.AppActivity

class DeepLinkActivity : AppActivity<String, DeepLinkViewModel>() {
    override fun getViewModel(): DeepLinkViewModel {
        return ViewModelProvider(this).get(DeepLinkViewModel::class.java)
    }

    override fun initVariables() {
        TODO("Not yet implemented")
    }

    override fun attributeViews() {
        setContentView(R.layout.activity_deep_link)
    }

    override fun populateData(data: String) {
        TODO("Not yet implemented")
    }
}
