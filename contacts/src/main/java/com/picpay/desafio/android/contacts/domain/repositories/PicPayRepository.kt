package com.picpay.desafio.android.contacts.domain.repositories

import com.picpay.desafio.android.contacts.domain.entities.User

interface PicPayRepository {
    suspend fun getAllUsers(): Result<List<User>>
}