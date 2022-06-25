package com.picpay.desafio.android.contacts.ui.commons

import android.content.Context
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.domain.usecase.ContactsUseCasesMessageProvider

class ContactsUseCasesMessageProviderImpl(
    context: Context
): ContactsUseCasesMessageProvider {
    override val defaultError: String = context.getString(R.string.error)
}