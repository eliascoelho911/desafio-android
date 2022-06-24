package com.picpay.desafio.android.contacts.data.services

import com.picpay.desafio.android.contacts.data.dtos.UserDTO
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<UserDTO>
}