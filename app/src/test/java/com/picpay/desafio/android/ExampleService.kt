package com.picpay.desafio.android

import com.picpay.desafio.android.contacts.data.dataSources.remote.ContactsRemoteDataSource

class ExampleService(
    private val service: ContactsRemoteDataSource
) {

    fun example(): List<com.picpay.desafio.android.contacts.domain.repositories.entities.User> {
        val users = service.getAllContacts().execute()

        return users.body() ?: emptyList()
    }
}