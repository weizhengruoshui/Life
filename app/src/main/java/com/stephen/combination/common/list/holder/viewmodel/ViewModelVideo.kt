    package com.stephen.combination.common.list.holder.viewmodel

import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.yaya.data.viewholder.RecyclerViewVideoItem
import com.stephen.player.PlayerConfiguration
import com.stephen.player.PlayerViewAffinity

class ViewModelVideo(private val playerViewAffinity: PlayerViewAffinity) : RecyclerViewModel(),
    PlayerViewAffinity.PlayerStateListener {

    var recyclerViewVideoItem: RecyclerViewVideoItem? = null

    var updateVideoItemPlayerListener: UpdateVideoItemPlayerListener? = null

    fun playVideo(
        styledPlayerView: StyledPlayerView,
        updateVideoItemPlayerListener: UpdateVideoItemPlayerListener
    ) {
        this.updateVideoItemPlayerListener = updateVideoItemPlayerListener
        playerViewAffinity.configuration =
            PlayerConfiguration(recyclerViewVideoItem?.videoUrl)
        playerViewAffinity.initializeItemVideo(
            styledPlayerView,
            this
        )
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when (playbackState) {
            Player.STATE_BUFFERING -> bufferingState()
            Player.STATE_READY -> readyState()
            Player.STATE_ENDED -> endedState()
        }
    }

    fun stopVideo() {
        playerViewAffinity.releaseItemVideo()
        updateVideoItemPlayerListener?.showPlayer(false)
    }

    private fun bufferingState() {
        updateVideoItemPlayerListener?.updatePlayerDimensions(playerViewAffinity.getAspectRatio())
    }

    private fun readyState() {
        updateVideoItemPlayerListener?.showPlayer(true)
    }

    private fun endedState() {
        stopVideo()
        updateVideoItemPlayerListener?.showPlayer(false)
    }

    interface UpdateVideoItemPlayerListener {
        fun showPlayer(shouldShow: Boolean)

        fun updatePlayerDimensions(aspectRatio: Float)
    }
}