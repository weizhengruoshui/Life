package com.yaya.image

import android.app.Activity
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

class ImageLoader {

    companion object {
        fun loadImage(
            fragment: Fragment,
            url: String?,
            imageView: ImageView
        ) {
            Glide.with(fragment).clear(imageView)
            val imageUrl: String? = changeHttpToHttps(url)

            if (imageUrl != null) {
                imageView.visibility = View.VISIBLE
                Glide.with(fragment)
                    .load(imageUrl)
                    .fallback(R.drawable.background)
                    .into(imageView)
            }
        }

        fun loadImage(
            activity: Activity,
            url: String?,
            imageView: ImageView
        ) {
            Glide.with(activity).clear(imageView)
            val imageUrl: String? = changeHttpToHttps(url)

            if (imageUrl != null) {
                imageView.visibility = View.VISIBLE
                Glide.with(activity)
                    .load(imageUrl)
                    .fallback(R.drawable.background)
                    .into(imageView)
            }
        }

        private const val HTTP: String = "http"
        private const val HTTPS: String = "https"

        private fun changeHttpToHttps(url: String?): String? {
            return if (url != null && url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
                url.replaceFirst(
                    HTTP,
                    HTTPS
                )
            } else {
                url
            }
        }
    }
}