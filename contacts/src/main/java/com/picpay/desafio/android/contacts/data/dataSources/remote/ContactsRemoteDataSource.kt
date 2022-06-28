package com.picpay.desafio.android.contacts.data.dataSources.remote

import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import retrofit2.http.GET

internal interface ContactsRemoteDataSource {

    @GET("users")
    suspend fun getAllContacts(): List<ContactDTO>
}