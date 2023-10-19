package com.yaya.life.screen.deeplink

import androidx.lifecycle.ViewModelProvider
import com.yaya.life.R
import com.yaya.life.common.AppActivity

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
