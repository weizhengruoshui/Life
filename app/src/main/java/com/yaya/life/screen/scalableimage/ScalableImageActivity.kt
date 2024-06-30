package com.yaya.life.screen.scalableimage

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.yaya.image.ImageLoader
import com.yaya.life.basic.AppActivity
import com.yaya.life.databinding.ActivityScalableImageBinding

class ScalableImageActivity : AppActivity() {

    private var imageUrl: String? = null
    private lateinit var binding: ActivityScalableImageBinding

    companion object {
        fun initStartIntent(intent: Intent, extraString: String) {
            intent.putExtra(ScalableImageModel.IMAGE_URL, extraString)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScalableImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        populateData()
    }

    private fun populateData() {
        imageUrl = intent.getStringExtra(ScalableImageModel.IMAGE_URL)
        ImageLoader.loadImage(this, imageUrl, binding.scalableImageView)
    }

}