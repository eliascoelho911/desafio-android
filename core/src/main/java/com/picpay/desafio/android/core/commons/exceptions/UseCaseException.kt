package com.picpay.desafio.android.core.commons.exceptions

class UseCaseException(
    override val message: String,
    override val cause: Throwable,
) : RuntimeException()