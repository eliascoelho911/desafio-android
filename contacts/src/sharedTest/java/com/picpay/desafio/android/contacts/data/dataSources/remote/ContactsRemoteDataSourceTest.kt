package com.picpay.desafio.android.contacts.data.dataSources.remote

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.core.test.BaseTest
import com.picpay.desafio.android.core.test.errorResponse
import com.picpay.desafio.android.core.test.readResourceAsText
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(AndroidJUnit4::class)
class ContactsRemoteDataSourceTest: BaseTest() {

    @Before
    fun setup() {
        server.start(serverPort)
    }

    @After
    fun tearDown() {
        server.close()
    }

    @Test
    internal fun `should return allContacts`() = runBlocking {
        val result = dataSource.getAllContacts()
        val expected = ContactDTO(
            id = 1,
            fullName = "Sandrine Spinka",
            imgUrl = "https://randomuser.me/api/portraits/men/1.jpg",
            username = "Tod86"
        )
        assertEquals(1, result.size)
        assertEquals(expected, result.first())
    }

    private val context by lazy {
        ApplicationProvider.getApplicationContext<Context>()
    }

    private val successResponse by lazy {
        MockResponse().setResponseCode(200).setBody(readResourceAsText(context, "contacts.json"))
    }

    private val serverPort = 8080

    private val server by lazy {
        MockWebServer().apply {
            dispatcher = object : Dispatcher() {
                override fun dispatch(request: RecordedRequest): MockResponse {
                    return when (request.path) {
                        "/users" -> successResponse
                        else -> errorResponse
                    }
                }
            }
        }
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val dataSource by lazy {
        retrofit.create(ContactsRemoteDataSource::class.java)
    }
}