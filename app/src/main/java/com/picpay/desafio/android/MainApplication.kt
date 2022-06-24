package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.core.di.coreModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        koinApplication {
            androidContext(this@MainApplication)
            modules(coreModule)
        }
    }
}