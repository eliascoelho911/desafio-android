package com.picpay.desafio.android.contacts.domain.repositories

import com.picpay.desafio.android.contacts.domain.entities.Contact

internal interface ContactsRepository {
    suspend fun getAllContacts(): Result<List<Contact>>
}