package com.picpay.desafio.android.contacts.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.domain.repositories.ContactsRepository
import com.picpay.desafio.android.contacts.ui.vo.ContactVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ContactsViewModel(
    private val contactsRepository: ContactsRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(ContactsUiState())
    val uiState = _uiState.asStateFlow()

    fun refreshContacts() {
        viewModelScope.launch {
            _uiState.update { currentUiState ->
                currentUiState.copy(isLoading = true)
            }
        }
    }
}

//todo notificação de erros
internal data class ContactsUiState(
    val isLoading: Boolean = false,
    val contacts: List<ContactVO> = emptyList(),
    val messageToUser: String? = null
)