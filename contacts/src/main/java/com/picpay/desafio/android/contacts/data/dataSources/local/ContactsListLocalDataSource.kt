package com.picpay.desafio.android.contacts.data.dataSources.local

import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.core.commons.data.LocalDataSource

internal class ContactsListLocalDataSource : LocalDataSource<String, List<ContactDTO>>()