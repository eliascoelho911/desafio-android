package com.picpay.desafio.android.contacts.domain.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class GetAllContactsTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

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