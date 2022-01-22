package com.stephen.combination.screen.scalableimage

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.common.AppActivity
import com.stephen.combination.databinding.ActivityScalableImageBinding
import com.yaya.image.ImageLoader

class ScalableImageActivity : AppActivity<String, ScalableImageModel>() {

    private var imageUrl: String? = null
    private lateinit var binding: ActivityScalableImageBinding

    companion object {
        fun initStartIntent(intent: Intent, extraString: String) {
            intent.putExtra(ScalableImageModel.IMAGE_URL, extraString)
        }
    }

    override fun initVariables() {
    }

    override fun getViewModel(): ScalableImageModel {
        return ViewModelProvider(this).get(ScalableImageModel::class.java)
    }

    override fun attributeViews() {
        binding = ActivityScalableImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun populateData(data: String) {
        imageUrl = intent.getStringExtra(data)
        ImageLoader.loadImage(this, imageUrl, binding.scalableImageView)
    }

}