package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.data.repositories.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.data.services.PicPayService
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.contacts.domain.usecase.ContactsUseCasesMessageProvider
import com.picpay.desafio.android.contacts.domain.usecase.GetAllContacts
import com.picpay.desafio.android.contacts.ui.commons.ContactsUseCasesMessageProviderImpl
import com.picpay.desafio.android.contacts.ui.contactsList.ContactsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val ContactsModule = module {
    viewModels()
    data()
    useCases()
    messageProviders()
}

private fun Module.useCases() {
    single { GetAllContacts(get(), get()) }
}

private fun Module.messageProviders() {
    single<ContactsUseCasesMessageProvider> { ContactsUseCasesMessageProviderImpl(get()) }
}

private fun Module.data() {
    single<ContactsRepository> { ContactsRepositoryImpl(get()) }
    single { get<Retrofit>().create(PicPayService::class.java) }
}

private fun Module.viewModels() {
    viewModel { ContactsListViewModel(get()) }
}
