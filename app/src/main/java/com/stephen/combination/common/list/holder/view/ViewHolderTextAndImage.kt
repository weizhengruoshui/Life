package com.stephen.combination.common.list.holder.view

import android.view.View
import androidx.core.app.ActivityOptionsCompat
import com.stephen.combination.R
import com.stephen.combination.common.AppListFragment
import com.stephen.combination.common.list.holder.viewmodel.ViewModelTextAndImage
import com.stephen.combination.common.manager.activity.AppActivityRouter
import com.stephen.combination.databinding.ViewHolderImageBinding
import com.yaya.data.viewholder.RecyclerViewTextAndImageItem
import com.yaya.image.ImageLoader
import javax.inject.Inject

class ViewHolderTextAndImage(
    private val viewHolderImageBinding: ViewHolderImageBinding,
    private val parentFragmentApp: AppListFragment<*, *>
) : RecyclerViewHolder<RecyclerViewTextAndImageItem>(
    viewHolderImageBinding.root,
    parentFragmentApp
) {

    @Inject
    lateinit var viewModelTextAndImage: ViewModelTextAndImage

    override fun bindData(data: RecyclerViewTextAndImageItem) {
        viewHolderComponent.inject(this)
        viewModelTextAndImage.recyclerViewTextAndImageItem = data
        populateView()
    }

    private fun onClickMore(view: View) {
        when (view.id) {
            R.id.image_view_holder_title ->
                viewModelTextAndImage.generateWebsiteInfo()?.also { webSiteInformation ->
                    AppActivityRouter.startWebViewActivity(
                        parentFragmentApp.context,
                        webSiteInformation,
                        ActivityOptionsCompat.makeSceneTransitionAnimation(
                            parentFragmentApp.parentActivity,
                            viewHolderImageBinding.imageViewHolderImage,
                            getString(R.string.transition_view_holder_image)
                        )
                    )
                }
            R.id.image_view_holder_image -> {
                com.yaya.utils.LogUtils.logD(javaClass.simpleName, "click on image")
                AppActivityRouter.startScalableImageActivity(
                    parentFragmentApp.context,
                    viewModelTextAndImage.getFirstImageUrl()
                )
            }
            else -> com.yaya.utils.LogUtils.logD(javaClass.simpleName, "Didn't handle ${view.id}")
        }
    }

    private fun populateView() {
        ImageLoader.loadImage(
            parentFragmentApp,
            viewModelTextAndImage.getFirstImageUrl(),
            viewHolderImageBinding.imageViewHolderImage
        )
        viewHolderImageBinding.imageViewHolderAuthor.apply {
            text = viewModelTextAndImage.recyclerViewTextAndImageItem?.authorName
            setOnClickListener(this@ViewHolderTextAndImage::onClickMore)
        }
        viewHolderImageBinding.imageViewHolderTime.apply {
            text = viewModelTextAndImage.recyclerViewTextAndImageItem?.date
            setOnClickListener(this@ViewHolderTextAndImage::onClickMore)
        }
        viewHolderImageBinding.imageViewHolderTitle.apply {
            text = viewModelTextAndImage.recyclerViewTextAndImageItem?.title
            setOnClickListener(this@ViewHolderTextAndImage::onClickMore)
        }
    }

    override fun onViewRecycled() {

    }
}