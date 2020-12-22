package com.stephen.combination.screen.webview

import com.stephen.combination.common.viewmodel.BaseViewModel

class WebViewModel : BaseViewModel<String>() {

    companion object {
        const val LINK_INFO = "link info"
    }

    override fun loadData() {
        liveData.value = LINK_INFO
    }
}