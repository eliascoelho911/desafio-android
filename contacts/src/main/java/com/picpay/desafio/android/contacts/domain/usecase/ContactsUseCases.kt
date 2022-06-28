package com.picpay.desafio.android.contacts.domain.usecase

import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository

internal class GetAllContacts(
    private val contactsRepository: ContactsRepository,
) {
    suspend operator fun invoke() = contactsRepository.getAllContacts()
}