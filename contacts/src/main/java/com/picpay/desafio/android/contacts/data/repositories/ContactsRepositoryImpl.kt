package com.picpay.desafio.android.contacts.data.repositories

import com.picpay.desafio.android.contacts.data.converters.toEntity
import com.picpay.desafio.android.contacts.data.dataSources.local.ContactsListLocalDataSource
import com.picpay.desafio.android.contacts.data.dataSources.remote.ContactsRemoteDataSource
import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import kotlinx.coroutines.delay

private const val ALL_CONTACTS_KEY = "AllContacts"

internal class ContactsRepositoryImpl(
    private val remoteDataSource: ContactsRemoteDataSource,
    private val localDataSource: ContactsListLocalDataSource,
) : ContactsRepository {
    override suspend fun getAllContacts() = runCatching {
        val data = localDataSource.get(ALL_CONTACTS_KEY) ?: getAllContactsOnRemote()
        localDataSource.set(ALL_CONTACTS_KEY, data)
        data.map { it.toEntity() }
    }

    private suspend fun getAllContactsOnRemote(): List<ContactDTO> {
        delay(2000)
        return remoteDataSource.getAllContacts()
    }
}