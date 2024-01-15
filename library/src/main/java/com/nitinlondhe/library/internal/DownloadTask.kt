package com.nitinlondhe.library.internal

import com.nitinlondhe.library.httpclient.HttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DownloadTask(private val httpClient: HttpClient, private val request: DownloadRequest) {

    suspend fun run(
        onStart: () -> Unit = {},
        onProgress: (value: Int) -> Unit = {_->},
        onPause: () -> Unit = {},
        onCompleted: () -> Unit = {},
        onError: (error: String) -> Unit = {_->}
    ) {
        withContext(Dispatchers.IO) {
            // dummy code for downloading the file

            onStart()

            // use of HttpClient
            httpClient.connect(request)

            // simulate read data from internet
            for (i in 1..100) {
                delay(100)
                onProgress(i)
            }

            onCompleted()

        }
    }
}
