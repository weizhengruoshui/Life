package com.stephen.player.download

import android.app.Application
import android.net.Uri
import com.google.android.exoplayer2.database.DatabaseProvider
import com.google.android.exoplayer2.database.ExoDatabaseProvider
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadRequest
import com.google.android.exoplayer2.offline.StreamKey
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.upstream.cache.Cache
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

/**
 * Note: This should be a singleton in your app.
 * This is a class to help download service create download manager and other essential fields
 */
class DownloadServiceHelper(private val application: Application) {

    private val databaseProvider: DatabaseProvider by lazy {
        ExoDatabaseProvider(application)
    }

    private val downloadDirectory: File =
        application.getExternalFilesDir(null) ?: application.filesDir

    // A download cache should not evict media, so should use a NoopCacheEvictor.
    private val downloadCache: Cache by lazy {
        SimpleCache(downloadDirectory, NoOpCacheEvictor(), databaseProvider)
    }

    val downloadManager: DownloadManager by lazy {
        DownloadManager(
            application,
            databaseProvider,
            downloadCache,
            DefaultHttpDataSourceFactory("")
        )
    }

    fun initiateDownloadManager() {
        downloadManager.maxParallelDownloads = 1
    }

    fun createDownloadRequest(
        requestId: String,
        requestType: String,
        contentUri: Uri,
        streamKeys: List<StreamKey>,
        customCacheKey: String,
        appData: ByteArray
    ): DownloadRequest {
        return DownloadRequest.Builder(requestId, contentUri)
            .setMimeType(requestType)
            .setStreamKeys(streamKeys)
            .setCustomCacheKey(customCacheKey)
            .setData(appData)
            .build()
    }

}