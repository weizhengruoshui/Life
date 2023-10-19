package com.yaya.player.download

import android.app.Application
import android.net.Uri
import com.google.android.exoplayer2.database.DatabaseProvider
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.offline.DownloadManager
import com.google.android.exoplayer2.offline.DownloadRequest
import com.google.android.exoplayer2.offline.StreamKey
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.Cache
import com.google.android.exoplayer2.upstream.cache.NoOpCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

/**
 * Note: This should be a singleton in your app.
 * This is a class to help download service create download manager and other essential fields
 */
class DownloadServiceHelper(private val application: Application) {

    // Note: This should be a singleton in your app.
    private val databaseProvider: DatabaseProvider by lazy {
        StandaloneDatabaseProvider(application)
    }

    private val downloadDirectory: File =
        application.getExternalFilesDir(null) ?: application.filesDir

    // A download cache should not evict media, so should use a NoopCacheEvictor.
    private val downloadCache: Cache by lazy {
        SimpleCache(downloadDirectory, NoOpCacheEvictor(), databaseProvider)
    }

    // Create a factory for reading the data from the network.
    private val dataSourceFactory: DefaultHttpDataSource.Factory by lazy {
        DefaultHttpDataSource.Factory()
    }

    // Choose an executor for downloading data. Using Runnable::run will cause each download task to
    // download data on its own thread. Passing an executor that uses multiple threads will speed up
    // download tasks that can be split into smaller parts for parallel execution. Applications that
    // already have an executor for background downloads may wish to reuse their existing executor.
    private val downloadExecutor = Runnable::run

    val downloadManager: DownloadManager by lazy {
        DownloadManager(
            application,
            databaseProvider,
            downloadCache,
            dataSourceFactory,
            downloadExecutor
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