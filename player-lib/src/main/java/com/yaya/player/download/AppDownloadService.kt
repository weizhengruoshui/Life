package com.yaya.player.download

import android.app.Notification
import android.net.Uri
import com.google.android.exoplayer2.offline.Download
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadService
import com.google.android.exoplayer2.offline.StreamKey
import com.google.android.exoplayer2.scheduler.Scheduler

/**
 * This is a class to implement exoplayer download service
 */

class AppDownloadService(
    foregroundId: Int = 1,
    foregroundNotificationUpdateInterval: Long = 1000,
    downloadNotificationChannelId: String? = null,
    downloadNotificationChannelName: Int = 0,
    channelDescriptionResourceId: Int = 0,
    private val downloadServiceHelper: DownloadServiceHelper
) : DownloadService(
    foregroundId,
    foregroundNotificationUpdateInterval,
    downloadNotificationChannelId,
    downloadNotificationChannelName,
    channelDescriptionResourceId
) {
    override fun getDownloadManager(): DownloadManager {
        downloadServiceHelper.initiateDownloadManager()
        return downloadServiceHelper.downloadManager
    }

    override fun getScheduler(): Scheduler? {
        TODO("Not yet implemented")
    }

    override fun getForegroundNotification(
        downloads: MutableList<Download>,
        notMetRequirements: Int
    ): Notification {
        TODO("Not yet implemented")
    }

    fun addDownload(
        requestId: String,
        requestType: String,
        contentUri: Uri,
        streamKeys: List<StreamKey>,
        customCacheKey: String,
        appData: ByteArray
    ) {
        sendAddDownload(
            application,
            AppDownloadService::class.java,
            downloadServiceHelper.createDownloadRequest(
                requestId,
                requestType,
                contentUri,
                streamKeys,
                customCacheKey,
                appData
            ),
            false
        )
    }

    fun removeDownload(requestId: String) {
        sendRemoveDownload(application, AppDownloadService::class.java, requestId, false)
    }

    fun stopDownload(requestId: String?, stopReason: Int) {
        sendSetStopReason(application, AppDownloadService::class.java, requestId, stopReason, false)
    }

    fun pauseAllDownloads() {
        sendPauseDownloads(application, AppDownloadService::class.java, false)
    }

    fun resumeAllDownloads() {
        sendResumeDownloads(application, AppDownloadService::class.java, false)
    }



}