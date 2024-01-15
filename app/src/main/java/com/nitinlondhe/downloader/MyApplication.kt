package com.nitinlondhe.downloader

import android.app.Application
import com.nitinlondhe.library.internal.Downloader

class MyApplication: Application() {

    lateinit var downloader: Downloader

    override fun onCreate() {
        super.onCreate()
        downloader = Downloader.create()
    }
}