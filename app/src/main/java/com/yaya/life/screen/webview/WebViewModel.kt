package com.yaya.life.screen.webview

import com.yaya.life.common.viewmodel.BaseViewModel

class WebViewModel : BaseViewModel<String>() {

    companion object {
        const val LINK_INFO = "link info"
    }

    override fun loadData() {
        data.value = LINK_INFO
    }
}