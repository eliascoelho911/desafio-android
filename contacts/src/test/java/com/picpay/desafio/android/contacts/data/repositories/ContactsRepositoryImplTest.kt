package com.picpay.desafio.android.contacts.data.repositories

import com.picpay.desafio.android.contacts.data.dataSources.local.ContactsListLocalDataSource
import com.picpay.desafio.android.contacts.data.dataSources.remote.ContactsRemoteDataSource
import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.core.test.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactsRepositoryImplTest : BaseTest() {

    @Test
    fun givenLocalDataSourceWithCache_whenGetAllContacts_shouldReturnCache() = runBlocking {
        every { localDataSource.get(any()) } returns contactsDtoList

        val result = repository.getAllContacts()

        coVerify(exactly = 0) { remoteDataSource.getAllContacts() }
        assertEquals(Result.success(contactsList), result)
    }

    @Test
    fun givenLocalDataSourceWithoutCache_whenGetAllContacts_shouldReturnRemoteData() = runBlocking {
        every { localDataSource.get(any()) } returns null
        coEvery { remoteDataSource.getAllContacts() } returns contactsDtoList

        val result = repository.getAllContacts()

        coVerify { localDataSource.set(any(), contactsDtoList) }
        assertEquals(Result.success(contactsList), result)
    }

    @Test
    fun givenLocalDataSourceWithoutCacheAndErrorOnGetRemoteData_whenGetAllContacts_shouldReturnFailure() =
        runBlocking {
            val exception = RuntimeException()

            every { localDataSource.get(any()) } returns null
            coEvery { remoteDataSource.getAllContacts() } throws exception

            val result = repository.getAllContacts()

            assertEquals(Result.failure<List<ContactDTO>>(exception), result)
        }

    private val contactsDtoList = listOf(ContactDTO(0, "imgUrl", "fullName", "username"))
    private val contactsList = listOf(Contact(0, "imgUrl", "fullName", "username"))
    private val remoteDataSource = mockk<ContactsRemoteDataSource>()
    private val localDataSource = mockk<ContactsListLocalDataSource>(relaxed = true)
    private val repository = ContactsRepositoryImpl(remoteDataSource, localDataSource)
}