package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.data.dataSources.local.ContactsListLocalDataSource
import com.picpay.desafio.android.contacts.data.dataSources.remote.ContactsRemoteDataSource
import com.picpay.desafio.android.contacts.data.repositories.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.contacts.domain.usecase.GetAllContacts
import com.picpay.desafio.android.contacts.ui.contactsList.ContactsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

val ContactsModule = module {
    viewModels()
    data()
    useCases()
}

private fun Module.viewModels() {
    viewModel { ContactsListViewModel(get(), get()) }
}

private fun Module.data() {
    repositories()
    remoteDataSources()
    localDataSources()
}

private fun Module.repositories() {
    single<ContactsRepository> { ContactsRepositoryImpl(get(), get()) }
}

private fun Module.remoteDataSources() {
    single { get<Retrofit>().create(ContactsRemoteDataSource::class.java) }
}

private fun Module.localDataSources() {
    single { ContactsListLocalDataSource() }
}

private fun Module.useCases() {
    single { GetAllContacts(get()) }
}
