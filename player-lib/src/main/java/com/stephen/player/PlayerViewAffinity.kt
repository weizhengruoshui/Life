package com.stephen.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.EventLogger
import com.yaya.utils.LogUtils

class PlayerViewAffinity(private val context: Context) {
    var configuration: PlayerConfiguration? = null
    var player: SimpleExoPlayer? = null
    private var playWhenReady = false
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    fun initializeWithState(playerStateListener: PlayerStateListener? = null) {
        val trackSelector = DefaultTrackSelector(context)
        player = SimpleExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
        player?.also { simplePlayer ->
            simplePlayer.addListener(PlayerEventListener(playerStateListener))
            simplePlayer.addAnalyticsListener(PlayerAnalyticsListener())
            simplePlayer.addAnalyticsListener(EventLogger(trackSelector))
            simplePlayer.seekTo(currentWindow, playbackPosition)
            simplePlayer.playWhenReady = playWhenReady
            simplePlayer.prepare(createMediaSource(), false, false)
        }
    }

    fun initializeItemVideo(
        playerView: PlayerView,
        playerStateListener: PlayerStateListener? = null
    ) {
        releaseItemVideo()
        val trackSelector = DefaultTrackSelector(context)
        player = SimpleExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
        playerView.player = player
        player?.also { simplePlayer ->
            simplePlayer.volume = 0f
            simplePlayer.addListener(PlayerEventListener(playerStateListener))
            simplePlayer.addAnalyticsListener(PlayerAnalyticsListener())
            simplePlayer.addAnalyticsListener(EventLogger(trackSelector))
            simplePlayer.playWhenReady = true
            simplePlayer.prepare(createMediaSource(), false, false)
        }
    }

    fun releaseWithState() {
        player?.also { simpleExoPlayer ->
            playbackPosition = simpleExoPlayer.currentPosition
            currentWindow = simpleExoPlayer.currentWindowIndex
            playWhenReady = simpleExoPlayer.playWhenReady
            simpleExoPlayer.release()
        }

        player = null
    }

    fun releaseItemVideo() {
        player?.release()
        player = null
    }

    fun getAspectRatio(): Float {
        return player?.run {
            videoFormat?.run {
                height / width.toFloat()
            }
        } ?: 9 / 16.toFloat()
    }

    private fun createMediaSource(): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(context, "Stephen")
        val progressiveMediaSourceFactory = ProgressiveMediaSource.Factory(dataSourceFactory)
        return progressiveMediaSourceFactory.createMediaSource(configuration?.uri!!)
    }

    interface PlayerStateListener {
        fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int)
    }

    private inner class PlayerEventListener(val playerStateListener: PlayerStateListener?) :
        Player.EventListener {
        override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
            playerStateListener?.onPlayerStateChanged(playWhenReady, playbackState)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            com.yaya.utils.LogUtils.logD(
                javaClass.simpleName,
                "isPlaying: $isPlaying"
            )
        }

        override fun onPlayerError(error: ExoPlaybackException) {
            com.yaya.utils.LogUtils.logD(
                javaClass.simpleName,
                "play error: $error"
            )
        }

        override fun onPositionDiscontinuity(reason: Int) {
            com.yaya.utils.LogUtils.logD(
                javaClass.simpleName,
                "position changed reason: $reason"
            )
        }
    }

    private inner class PlayerAnalyticsListener : AnalyticsListener {
        override fun onSeekStarted(eventTime: AnalyticsListener.EventTime) {
            com.yaya.utils.LogUtils.logD(
                javaClass.simpleName,
                "onSeekStarted"
            )
        }
    }
}