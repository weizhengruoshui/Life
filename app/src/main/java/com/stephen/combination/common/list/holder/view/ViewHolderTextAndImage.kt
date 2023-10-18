package com.stephen.combination.common.list.holder.view

import android.view.View
import com.stephen.combination.R
import com.stephen.combination.common.manager.AppActivityRouter
import com.stephen.combination.databinding.ViewHolderImageBinding
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.yaya.data.webview.WebSiteInformation
import com.yaya.utils.LogUtils

class ViewHolderTextAndImage(
    private val viewHolderImageBinding: ViewHolderImageBinding,
) : RecyclerViewHolder<RecyclerViewTextAndImageItem>(
    viewHolderImageBinding.root
) {
    private var itemData: RecyclerViewTextAndImageItem? = null

    override fun bindData(data: RecyclerViewTextAndImageItem) {
        itemData = data
        populateView()
    }

    private fun onClickEvent(view: View) {
        when (view.id) {
            R.id.image_view_holder_title ->
                itemData?.run {
                    WebSiteInformation(link, authorName, imageUrl)
                } //todo jump to web activity here

            R.id.image_view_holder_image -> {
                LogUtils.logD(javaClass.simpleName, "click on image")
                AppActivityRouter.startScalableImageActivity(
                    view.context,
                    itemData?.imageUrl
                )
            }

            else -> LogUtils.logD(javaClass.simpleName, "Didn't handle ${view.id}")
        }
    }

    private fun populateView() {
        //todo show the pic here

        viewHolderImageBinding.imageViewHolderAuthor.apply {
            text = itemData?.authorName
            setOnClickListener(this@ViewHolderTextAndImage::onClickEvent)
        }
        viewHolderImageBinding.imageViewHolderTime.apply {
            text = itemData?.date
            setOnClickListener(this@ViewHolderTextAndImage::onClickEvent)
        }
        viewHolderImageBinding.imageViewHolderTitle.apply {
            text = itemData?.title
            setOnClickListener(this@ViewHolderTextAndImage::onClickEvent)
        }
    }

    override fun onViewRecycled() {

    }
}