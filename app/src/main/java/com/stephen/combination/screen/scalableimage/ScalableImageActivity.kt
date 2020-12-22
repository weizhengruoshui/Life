package com.stephen.combination.screen.scalableimage

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.stephen.combination.R
import com.stephen.combination.databinding.ActivityScalableImageBinding
import com.stephen.combination.common.BaseActivity
import com.yaya.image.ImageLoader

class ScalableImageActivity : BaseActivity<String, ScalableImageModel>() {

    private var imageUrl: String? = null
    private lateinit var binding: ActivityScalableImageBinding

    companion object {
        fun initStartIntent(intent: Intent, extraString: String) {
            intent.putExtra(ScalableImageModel.IMAGE_URL, extraString)
        }
    }

    override fun initVariables() {
        binding = ActivityScalableImageBinding.inflate(layoutInflater)
    }

    override fun getViewModel(): ScalableImageModel {
        return ViewModelProvider(this).get(ScalableImageModel::class.java)
    }

    override fun attributeViews() {
        setContentView(R.layout.activity_scalable_image)
    }

    override fun populateData(data: String) {
        imageUrl = intent.getStringExtra(data)
        ImageLoader.loadImage(this, imageUrl, binding.scalableImageView)
    }

}