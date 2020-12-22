package com.stephen.combination.common.list.holder.view

import com.stephen.combination.common.extension.showOrInvisible
import com.stephen.combination.common.list.holder.viewmodel.ViewModelVideo
import com.yaya.data.viewholder.RecyclerViewVideoItem
import com.stephen.combination.databinding.ViewHolderVideoBinding
import com.stephen.combination.common.AppListFragment
import javax.inject.Inject

class ViewHolderVideo(
    private val binding: ViewHolderVideoBinding,
    parentFragmentApp: AppListFragment<*, *>
) :
    RecyclerViewHolder<RecyclerViewVideoItem>(binding.root, parentFragmentApp),
    ViewModelVideo.UpdateVideoItemPlayerListener {

    @Inject
    lateinit var viewModelVideo: ViewModelVideo

    private var hasPopulatedPlayerLayout = false

    override fun bindData(data: RecyclerViewVideoItem) {
        viewHolderComponent.inject(this)
        viewModelVideo.recyclerViewVideoItem = data
        binding.videoPath.text = viewModelVideo.recyclerViewVideoItem?.name.toString()
    }

    fun playVideo() {
        viewModelVideo.playVideo(binding.videoItemPlayer, this)
    }

    fun stopVideo() {
        viewModelVideo.stopVideo()
    }

    override fun showPlayer(shouldShow: Boolean) {
        binding.videoItemCoverImage.showOrInvisible(!shouldShow)
        binding.videoItemPlayer.showOrInvisible(shouldShow)
    }

    override fun updatePlayerDimensions(aspectRatio: Float) {
        binding.videoItemPlayer.apply {
            if (hasPopulatedPlayerLayout) return
            val newHeight = width * aspectRatio
            layoutParams.height = newHeight.toInt()
            hasPopulatedPlayerLayout = true
        }
    }

    override fun onViewRecycled() {
        stopVideo()
    }
}