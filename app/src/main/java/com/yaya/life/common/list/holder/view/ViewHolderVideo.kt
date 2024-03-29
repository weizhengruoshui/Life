package com.yaya.life.common.list.holder.view

import com.yaya.life.common.extension.showOrInvisible
import com.yaya.life.common.list.holder.viewmodel.VideoItemViewModel
import com.yaya.life.databinding.ViewHolderVideoBinding
import com.yaya.player.PlayerViewAffinity
import com.yaya.data.viewholder.RecyclerViewVideoItem

class ViewHolderVideo(
    private val binding: ViewHolderVideoBinding,
) :
    RecyclerViewHolder<RecyclerViewVideoItem>(binding.root),
    VideoItemViewModel.UpdateVideoItemPlayerListener {

    private val videoItemViewModel =
        VideoItemViewModel(PlayerViewAffinity(itemView.context))

    private var hasPopulatedPlayerLayout = false

    override fun bindData(data: RecyclerViewVideoItem) {
        videoItemViewModel.recyclerViewVideoItem = data
        binding.videoPath.text = videoItemViewModel.recyclerViewVideoItem?.name.toString()
    }

    fun playVideo() {
        videoItemViewModel.playVideo(binding.videoItemPlayer, this)
    }

    fun stopVideo() {
        videoItemViewModel.stopVideo()
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