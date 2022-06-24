package com.picpay.desafio.android.contacts.data.services

import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import retrofit2.http.GET

internal interface PicPayService {

    @GET("users")
    suspend fun getAllContacts(): List<ContactDTO>
}