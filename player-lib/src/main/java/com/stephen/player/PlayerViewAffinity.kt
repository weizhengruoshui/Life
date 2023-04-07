package com.stephen.player

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.analytics.AnalyticsListener
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSource
import com.google.android.exoplayer2.util.EventLogger
import com.yaya.utils.LogUtils

class PlayerViewAffinity(private val context: Context) {
    var configuration: PlayerConfiguration? = null
    var player: ExoPlayer? = null
    private var playWhenReady = false
    private var currentWindow = 0
    private var playbackPosition: Long = 0

    fun initializeWithState(playerStateListener: PlayerStateListener? = null) {
        val trackSelector = DefaultTrackSelector(context)
        player = ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
        player?.also { exoPlayer ->
            exoPlayer.addListener(PlayerEventListener(playerStateListener))
            exoPlayer.addAnalyticsListener(PlayerAnalyticsListener())
            exoPlayer.addAnalyticsListener(EventLogger())
            exoPlayer.seekTo(currentWindow, playbackPosition)
            exoPlayer.playWhenReady = playWhenReady
            exoPlayer.setMediaSource(createMediaSource())
            exoPlayer.prepare()
        }
    }

    fun initializeItemVideo(
        playerView: StyledPlayerView,
        playerStateListener: PlayerStateListener? = null
    ) {
        releaseItemVideo()
        val trackSelector = DefaultTrackSelector(context)
        player = ExoPlayer.Builder(context)
            .setTrackSelector(trackSelector)
            .build()
        playerView.player = player
        player?.also { exoPlayer ->
            exoPlayer.volume = 0f
            exoPlayer.addListener(PlayerEventListener(playerStateListener))
            exoPlayer.addAnalyticsListener(PlayerAnalyticsListener())
            exoPlayer.addAnalyticsListener(EventLogger())
            exoPlayer.playWhenReady = true
            exoPlayer.setMediaSource(createMediaSource())
            exoPlayer.prepare()
        }
    }

    fun releaseWithState() {
        player?.also { exoPlayer ->
            playbackPosition = exoPlayer.currentPosition
            currentWindow = exoPlayer.currentMediaItemIndex
            playWhenReady = exoPlayer.playWhenReady
            exoPlayer.release()
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
        } ?: (9 / 16.toFloat())
    }

    private fun createMediaSource(): MediaSource {
        val dataSourceFactory: DataSource.Factory = DefaultDataSource.Factory(context)
        val progressiveMediaSourceFactory = ProgressiveMediaSource.Factory(dataSourceFactory)
        return progressiveMediaSourceFactory.createMediaSource(MediaItem.fromUri(configuration?.uri!!))
    }

    interface PlayerStateListener {
        fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int)
    }

    private inner class PlayerEventListener(val playerStateListener: PlayerStateListener?) :
        Player.Listener {

        override fun onPlayWhenReadyChanged(playWhenReady: Boolean, reason: Int) {
            super.onPlayWhenReadyChanged(playWhenReady, reason)
        }

        override fun onPlaybackStateChanged(playbackState: Int) {
            super.onPlaybackStateChanged(playbackState)
        }

        override fun onIsPlayingChanged(isPlaying: Boolean) {
            LogUtils.logD(
                javaClass.simpleName,
                "isPlaying: $isPlaying"
            )
        }

        override fun onPlayerError(error: PlaybackException) {
            LogUtils.logD(
                javaClass.simpleName,
                "play error: $error"
            )
        }

        override fun onPositionDiscontinuity(
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            LogUtils.logD(
                javaClass.simpleName,
                "position changed reason: $reason"
            )
        }
    }

    private inner class PlayerAnalyticsListener : AnalyticsListener {
        override fun onPositionDiscontinuity(
            eventTime: AnalyticsListener.EventTime,
            oldPosition: Player.PositionInfo,
            newPosition: Player.PositionInfo,
            reason: Int
        ) {
            LogUtils.logD(
                javaClass.simpleName,
                "onSeekStarted"
            )
        }
    }
}