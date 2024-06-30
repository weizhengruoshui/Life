package com.yaya.life.screen.webview

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.yaya.data.webview.WebSiteInformation
import com.yaya.image.ImageLoader
import com.yaya.life.basic.AppActivity
import com.yaya.life.databinding.ActivityWebViewBinding
import java.net.URLDecoder

class WebViewActivity : AppActivity() {

    private lateinit var binding: ActivityWebViewBinding

    companion object {
        fun initStartIntent(startIntent: Intent, linkInfo: WebSiteInformation) {
            startIntent.putExtra(WebViewModel.LINK_INFO, linkInfo)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.webAppBarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.webView.webViewClient = CustomizedWebViewClient()
        binding.webView.webChromeClient = CustomizedChromeClient()
        registerBackPressed()
        populateData()
    }

    private fun populateData() {
        val linkInfo: WebSiteInformation? = intent.getParcelableExtra(WebViewModel.LINK_INFO)
        linkInfo?.also { webSiteInformation ->
            supportActionBar?.title = webSiteInformation.siteTitle
            ImageLoader.loadImage(
                this,
                webSiteInformation.siteImage,
                binding.webAppBarLayout.toolbarImage
            )
            // do this because of the return url changed, can't use directly.
            var linkUrl = webSiteInformation.siteUrl
            URLDecoder.decode(linkUrl, "utf-8").also { decodedLinkUrl ->
                if (decodedLinkUrl.contains("=")) {
                    decodedLinkUrl?.split("=")
                        ?.get(1)
                        ?.let { secondUrl ->
                            if (secondUrl.startsWith("https", true)) {
                                linkUrl = secondUrl
                            }
                        }
                }
                binding.webView.loadUrl(linkUrl)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    private fun registerBackPressed() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                supportFinishAfterTransition()
            }
        })
    }
}
