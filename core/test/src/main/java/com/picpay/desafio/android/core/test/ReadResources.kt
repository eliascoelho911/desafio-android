package com.picpay.desafio.android.core.test

import android.content.Context

fun readResourceAsText(context: Context, name: String): String =
    context.classLoader.getResource(name).readText()

