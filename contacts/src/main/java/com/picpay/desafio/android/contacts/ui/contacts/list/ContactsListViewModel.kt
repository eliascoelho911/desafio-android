package com.picpay.desafio.android.contacts.ui.contacts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetAllContacts
import com.picpay.desafio.android.core.network.fold
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class ContactsListViewModel(
    private val getAllContacts: GetAllContacts,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ContactsListUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshContacts() {
        viewModelScope.launch {
            loadingState()
            getAllContacts().fold(
                onSuccess = { contacts ->
                    successOnGetAllContactsState(contacts)
                },
                onError = { message ->
                    errorState(message)
                }
            )
        }
    }

    private fun loadingState() {
        _uiState.value = ContactsListUiState(
            isLoading = true
        )
    }

    private fun successOnGetAllContactsState(contacts: List<Contact>) {
        _uiState.value = ContactsListUiState(
            contacts = contacts.mapToContactItem(),
        )
    }

    private fun errorState(message: String) {
        _uiState.value = ContactsListUiState(
            error = ErrorUiState(message = message, onClickTryAgain = { refreshContacts() })
        )
    }

    private fun List<Contact>.mapToContactItem(): List<ContactItemUiState> = map {
        ContactItemUiState(
            id = it.id,
            imgUrl = it.imgUrl,
            fullName = it.fullName,
            username = it.username
        )
    }
}

internal data class ContactsListUiState(
    val isLoading: Boolean = false,
    val contacts: List<ContactItemUiState> = emptyList(),
    val error: ErrorUiState? = null,
)

internal data class ContactItemUiState(
    val id: Int,
    val imgUrl: String,
    val fullName: String,
    val username: String,
)

internal data class ErrorUiState(
    val message: String,
    val onClickTryAgain: () -> Unit,
)