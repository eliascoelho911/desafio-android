package com.picpay.desafio.android.core.test

import android.app.Application
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        runCatching {
            Picasso.setSingletonInstance(Picasso.Builder(this).build())
        }
        runCatching {
            startKoin {
                androidContext(this@TestApplication)
                allowOverride(true)
            }
        }
    }
}