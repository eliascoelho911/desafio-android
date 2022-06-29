package com.picpay.desafio.android.contacts

import android.app.Application
import com.squareup.picasso.Picasso
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

//Todo: Mover para core
class TestApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        runCatching {
            Picasso.setSingletonInstance(Picasso.Builder(this).build())
        }
        startKoin {
            androidContext(this@TestApplication)
            allowOverride(true)
        }
    }
}