package com.stephen.combination.screen.scalableimage

import com.stephen.combination.common.viewmodel.BaseViewModel

class ScalableImageModel : BaseViewModel<String>() {

    companion object {
        const val IMAGE_URL = "image url"
    }

    override fun loadData() {
        liveData.value = IMAGE_URL
    }
}