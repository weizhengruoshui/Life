package com.stephen.combination.screen.webview

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.yaya.data.webview.WebSiteInformation
import com.stephen.combination.databinding.ActivityWebViewBinding
import com.stephen.combination.common.AppActivity
import com.yaya.image.ImageLoader
import java.net.URLDecoder

class WebViewActivity : AppActivity<String, WebViewModel>() {

    private lateinit var binding: ActivityWebViewBinding

    companion object {
        fun initStartIntent(startIntent: Intent, linkInfo: WebSiteInformation) {
            startIntent.putExtra(WebViewModel.LINK_INFO, linkInfo)
        }
    }

    override fun initVariables() {
        binding = ActivityWebViewBinding.inflate(layoutInflater)
    }

    override fun attributeViews() {
        setContentView(binding.root)
        setSupportActionBar(binding.webAppBarLayout.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
        binding.webView.webViewClient = CustomizedWebViewClient()
        binding.webView.webChromeClient = CustomizedChromeClient()
    }

    override fun populateData(data: String) {
        val linkInfo: WebSiteInformation? = intent.getParcelableExtra(data)
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

    override fun getViewModel(): WebViewModel {
        return ViewModelProvider(this).get(WebViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
