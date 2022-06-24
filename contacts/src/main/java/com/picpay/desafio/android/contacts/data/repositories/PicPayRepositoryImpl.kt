package com.picpay.desafio.android.contacts.data.repositories

import com.picpay.desafio.android.contacts.data.converters.toEntity
import com.picpay.desafio.android.contacts.data.services.PicPayService
import com.picpay.desafio.android.contacts.domain.repositories.PicPayRepository

class PicPayRepositoryImpl(private val service: PicPayService) : PicPayRepository {
    override suspend fun getAllUsers() = runCatching {
        service.getUsers().map { it.toEntity() }
    }
}