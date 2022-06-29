package com.picpay.desafio.android.contacts.ui.contactsList

import androidx.annotation.StringRes
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetAllContacts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

private const val ContactsKey = "contacts"

/**
 * Todo: Testar se refresh contacts inicia com loading
 * Todo: Testar se refresh contacts está buscando cache e enviando sucesso se encontra
 * Todo: Testar se refresh contacts está salvando cache quando chama getAllContacts e recebe sucesso
 * Todo: Testar se refresh contacts está deletando cache quando chama getAllContacts e recebe erro
 * Todo: Testar se refresh contacts está enviando erro quando recebe erro de getAllContacts
 * Todo: Testar se refresh contacts está chamando getAllContacts quando não tem cache
 */
internal class ContactsListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val getAllContacts: GetAllContacts,
) : ViewModel() {
    private val _uiState = MutableStateFlow(ContactsListUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshContacts() {
        loadingState()
        viewModelScope.launch {
            savedStateHandle.get<List<ContactItemUiState>>(ContactsKey)?.let { contacts ->
                successOnGetAllContactsState(contacts)
            } ?: run {
                getAllContacts().fold(
                    onSuccess = { contacts ->
                        val contactItems = contacts.mapToContactItem()
                        successOnGetAllContactsState(contactItems)
                        savedStateHandle.set(ContactsKey, contactItems)
                    },
                    onFailure = {
                        errorState(R.string.error)
                        savedStateHandle.set(ContactsKey, null)
                    }
                )
            }
        }
    }

    private fun loadingState() {
        _uiState.value = ContactsListUiState(
            isLoading = true
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

    private fun successOnGetAllContactsState(contacts: List<ContactItemUiState>) {
        _uiState.value = ContactsListUiState(
            contacts = contacts,
            scrollIsEnabled = true
        )
    }

    private fun errorState(@StringRes messageRes: Int) {
        _uiState.value = ContactsListUiState(
            error = ErrorUiState(
                messageRes = messageRes,
                onClickTryAgain = { refreshContacts() }
            )
        )
    }
}

internal data class ContactsListUiState(
    val isLoading: Boolean = false,
    val contacts: List<ContactItemUiState> = emptyList(),
    val error: ErrorUiState? = null,
    val scrollIsEnabled: Boolean = false,
)

internal data class ContactItemUiState(
    val id: Int,
    val imgUrl: String,
    val fullName: String,
    val username: String,
)

internal data class ErrorUiState(
    @StringRes val messageRes: Int,
    val onClickTryAgain: () -> Unit,
)