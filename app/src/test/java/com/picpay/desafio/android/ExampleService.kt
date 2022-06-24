package com.picpay.desafio.android

import com.picpay.desafio.android.contacts.data.services.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<com.picpay.desafio.android.contacts.domain.repositories.entities.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}