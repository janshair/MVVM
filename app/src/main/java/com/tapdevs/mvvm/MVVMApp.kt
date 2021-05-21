package com.tapdevs.mvvm

import android.app.Application
import com.tapdevs.core.injections.networkModule
import com.tapdevs.users.injection.usersModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MVVMApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger(Level.ERROR)
            }
            androidContext(this@MVVMApp)
            modules(listOf(networkModule, usersModule))
        }
    }
}
