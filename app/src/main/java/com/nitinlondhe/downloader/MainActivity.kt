package com.nitinlondhe.downloader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val downloader = (application as MyApplication).downloader
        val request = downloader.newBuilder("someUrl", "someDirPath", "someFileName")
            .readTimeout(10000)
            .connectTimeout(10000)
            .tag("someTag")
            .build()

        downloader.enqueue(request,
            onStart = {
                //binding.textViewStatus.text = "onStart"
            },
            onProgress = {
                //binding.textViewProgress.text = "$it %"
                //binding.progressBar.progress = it
            },
            onPause = {
                //binding.textViewStatus.text = "onPause"
            },
            onCompleted = {
                //binding.textViewStatus.text = "onCompleted"
            },
            onError = {
                //binding.textViewStatus.text = it
            })
    }
}