package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.contacts.data.dataSources.remote.ContactsRemoteDataSource
import junit.framework.Assert.assertEquals
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<ContactsRemoteDataSource>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() {
        // given
        val call = mock<Call<List<com.picpay.desafio.android.contacts.domain.repositories.entities.User>>>()
        val expectedUsers = emptyList<com.picpay.desafio.android.contacts.domain.repositories.entities.User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}