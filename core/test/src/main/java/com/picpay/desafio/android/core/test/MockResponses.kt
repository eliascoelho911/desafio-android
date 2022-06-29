package com.picpay.desafio.android.core.test

import okhttp3.mockwebserver.MockResponse

val errorResponse get() = MockResponse().setResponseCode(404)