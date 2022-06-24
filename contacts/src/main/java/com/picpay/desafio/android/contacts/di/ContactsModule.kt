package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.data.repositories.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.data.services.PicPayService
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.contacts.ui.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val ContactsModule = module {
    viewModels()
    data()
}

private fun Module.data() {
    single<ContactsRepository> { ContactsRepositoryImpl(get()) }
    single { get<Retrofit>().create(PicPayService::class.java) }
}

private fun Module.viewModels() {
    viewModel { ContactsViewModel(get()) }
}
