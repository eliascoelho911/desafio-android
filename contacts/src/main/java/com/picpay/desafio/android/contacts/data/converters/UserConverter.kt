package com.picpay.desafio.android.contacts.data.converters

import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.contacts.domain.entities.Contacts

internal fun ContactDTO.toEntity() = Contacts(
    imgUrl = imgUrl,
    fullName = fullName,
    id = id,
    username = username
)
