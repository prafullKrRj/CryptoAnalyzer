package com.example.crypto

import android.app.Application
import com.example.crypto.data.AppContainer
import com.example.crypto.data.DefaultAppContainer

class CryptoApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}