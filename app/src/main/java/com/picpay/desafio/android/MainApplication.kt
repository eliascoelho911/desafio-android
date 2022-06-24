package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.contacts.di.ContactsModule
import com.picpay.desafio.android.core.di.CoreModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(CoreModule, ContactsModule)
        }
    }
}