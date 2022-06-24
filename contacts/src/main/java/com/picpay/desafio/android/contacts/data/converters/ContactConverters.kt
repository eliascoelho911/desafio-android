package com.picpay.desafio.android.contacts.data.converters

import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.contacts.domain.entities.Contact

internal fun ContactDTO.toEntity() = Contact(
    imgUrl = imgUrl,
    fullName = fullName,
    id = id,
    username = username
)