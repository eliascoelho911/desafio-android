package com.picpay.desafio.android.contacts.domain.usecase

import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.core.test.BaseTest
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class GetAllContactsTest : BaseTest() {

    @Test
    fun whenInvokeShouldReturnRepositoryData() = runBlocking {
        val expected = Result.success<List<Contact>>(mockk())
        coEvery { contactsRepository.getAllContacts() } returns expected

        val result = getAllContacts()

        assertEquals(expected, result)
    }

    private val contactsRepository = mockk<ContactsRepository>()
    private val getAllContacts = GetAllContacts(contactsRepository)
}