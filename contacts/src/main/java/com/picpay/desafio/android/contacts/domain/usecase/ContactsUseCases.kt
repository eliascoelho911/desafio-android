package com.picpay.desafio.android.contacts.domain.usecase

import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.core.network.UseCaseResult

internal class GetAllContacts(
    private val contactsRepository: ContactsRepository,
    private val messageProvider: ContactsUseCasesMessageProvider,
) {
    suspend operator fun invoke(): UseCaseResult<List<Contact>> =
        contactsRepository.getAllContacts().getOrNull()?.let { data ->
            UseCaseResult.Success(data)
        } ?: UseCaseResult.Error(messageProvider.defaultError)
}

internal interface ContactsUseCasesMessageProvider {
    val defaultError: String
}