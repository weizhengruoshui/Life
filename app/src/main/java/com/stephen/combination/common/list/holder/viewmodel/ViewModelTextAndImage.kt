package com.stephen.combination.common.list.holder.viewmodel

import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.yaya.data.webview.WebSiteInformation

class ViewModelTextAndImage : RecyclerViewModel() {
    var recyclerViewTextAndImageItem: RecyclerViewTextAndImageItem? = null

    fun getFirstImageUrl(): String? {
        return recyclerViewTextAndImageItem?.imageUrl
    }

    fun generateWebsiteInfo(): WebSiteInformation? {
        return recyclerViewTextAndImageItem?.run {
            WebSiteInformation(link, authorName, imageUrl)
        }
    }
}