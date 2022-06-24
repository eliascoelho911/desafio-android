package com.picpay.desafio.android.contacts.data.converters

import com.picpay.desafio.android.contacts.data.dtos.UserDTO
import com.picpay.desafio.android.contacts.domain.entities.User

fun UserDTO.toEntity() = User(
    img = img,
    name = name,
    id = id,
    username = username
)
