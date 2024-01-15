package com.nitinlondhe.library.internal

import com.nitinlondhe.library.httpclient.DownloaderConfig
import com.nitinlondhe.library.internal.DownloadDispatcher
import com.nitinlondhe.library.internal.DownloadRequest
import com.nitinlondhe.library.internal.DownloadRequestQueue

class Downloader private constructor(private val config: DownloaderConfig){

    companion object {
        fun create(config: DownloaderConfig = DownloaderConfig()): Downloader {
            return Downloader(config)
        }
    }

    private val reqQueue = DownloadRequestQueue(DownloadDispatcher(config.httpClient))

    fun newBuilder(url: String, dirPath: String, fileName: String): DownloadRequest.Builder {
        return DownloadRequest.Builder(url, dirPath, fileName).readTimeout(config.readTimeOut)
            .connectTimeout(config.connectTimeOut)
    }

    fun enqueue(
        req: DownloadRequest,
        onStart: () -> Unit = {},
        onProgress: (value: Int) -> Unit = { _ -> },
        onPause: () -> Unit = {},
        onCompleted: () -> Unit = {},
        onError: (error: String) -> Unit = { _ -> }
    ): Int {
        req.onStart = onStart
        req.onProgress = onProgress
        req.onPause = onPause
        req.onCompleted = onCompleted
        req.onError = onError
        return reqQueue.enqueue(req)
    }

    fun pause(id: Int) {
        reqQueue.pause(id)
    }

    fun resume(id: Int) {
        reqQueue.resume(id)
    }

    fun cancel(id: Int) {
        reqQueue.cancel(id)
    }

    fun cancel(tag: String) {
        reqQueue.cancel(tag)
    }

    fun cancelAll() {
        reqQueue.cancelAll()
    }
}