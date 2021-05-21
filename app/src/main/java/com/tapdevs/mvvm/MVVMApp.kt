package com.tapdevs.mvvm

import android.app.Application
import com.tapdevs.mvvm.BuildConfig
import com.tapdevs.core.injections.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MVVMApp : Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@MVVMApp)
            modules(listOf(networkModule))
        }

    }
}