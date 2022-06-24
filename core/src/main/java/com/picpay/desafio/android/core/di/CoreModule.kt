package com.picpay.desafio.android.core.di

import com.google.gson.GsonBuilder
import com.picpay.desafio.android.core.BuildConfig
import okhttp3.OkHttpClient
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val CoreModule = module {
    network()
}

private fun Module.network() {
    single { GsonBuilder().create() }
    single { OkHttpClient.Builder().build() }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()
    }
}