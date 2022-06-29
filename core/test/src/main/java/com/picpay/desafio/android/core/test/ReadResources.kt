package com.picpay.desafio.android.core.test

import android.content.Context
import com.google.gson.Gson

fun readResourceAsText(context: Context, name: String): String =
    context.classLoader.getResource(name).readText()

inline fun <reified T> readResource(context: Context, name: String): T =
    Gson().fromJson(readResourceAsText(context, name), T::class.java)
