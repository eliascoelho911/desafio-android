package com.picpay.desafio.android.contacts.data.repositories

import com.picpay.desafio.android.contacts.data.converters.toEntity
import com.picpay.desafio.android.contacts.data.services.PicPayService
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import kotlinx.coroutines.delay

internal class ContactsRepositoryImpl(private val service: PicPayService) : ContactsRepository {
    override suspend fun getAllContacts() = runCatching {
        delay(2000)
        service.getAllContacts().map { it.toEntity() }
    }
}