package com.nitinlondhe.library.httpclient

import com.nitinlondhe.library.internal.DownloadRequest

interface HttpClient: Cloneable {

    fun connect(req: DownloadRequest)
}