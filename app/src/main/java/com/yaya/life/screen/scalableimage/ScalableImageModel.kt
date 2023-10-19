package com.yaya.life.screen.scalableimage

import com.yaya.life.common.viewmodel.BaseViewModel

class ScalableImageModel : BaseViewModel<String>() {

    companion object {
        const val IMAGE_URL = "image url"
    }

    override fun loadData() {
        data.value = IMAGE_URL
    }
}