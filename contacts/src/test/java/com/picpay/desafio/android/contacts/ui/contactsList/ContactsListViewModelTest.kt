package com.picpay.desafio.android.contacts.ui.contactsList

import androidx.lifecycle.SavedStateHandle
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.contacts.domain.usecase.GetAllContacts
import com.picpay.desafio.android.core.test.BaseTest
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ContactsListViewModelTest : BaseTest() {

    private val savedStateHandle = mockk<SavedStateHandle>()
    private val getAllContacts = mockk<GetAllContacts>()
    private val viewModel = ContactsListViewModel(savedStateHandle, getAllContacts)

    @Test
    fun whenCallRefreshContacts_shouldEmitLoadingState() = runBlocking {
        viewModel.refreshContacts()
        isLoadingState(viewModel.uiState.first())
    }

    @Test
    fun givenContactsSavedOnSavedStateHandleAndSuccessOnGetAllContacts_whenCallRefreshContacts_shouldEmitSuccess() =
        runBlocking {
            every { savedStateHandle.get<List<ContactItemUiState>>(CONTACTS_KEY) } returns CONTACT_LIST_ITEM_UI_STATE_MOCK

            viewModel.refreshContacts()

            isSuccessState(state = viewModel.uiState.value, contacts = CONTACT_LIST_ITEM_UI_STATE_MOCK)
            coVerify(exactly = 0) { getAllContacts() }
        }

    @Test
    fun givenContactsNotSavedOnSavedStateHandleAndFailureOnGetAllContacts_whenCallRefreshContacts_shouldEmitSuccessAndGetAllContacts() =
        runBlocking {
            contactsNotSavedOnSavedStateHandle()
            mockGetAllContactsResponse(Result.success(CONTACTS_LIST_MOCK))

            viewModel.refreshContacts()

            isSuccessState(state = viewModel.uiState.value, contacts = CONTACT_LIST_ITEM_UI_STATE_MOCK)
            verify { savedStateHandle.set(CONTACTS_KEY, CONTACT_LIST_ITEM_UI_STATE_MOCK) }
        }

    @Test
    fun givenContactsNotSavedOnSavedStateHandleAndFailureOnGetAllContacts_whenCallRefreshContacts_shouldEmitError() =
        runBlocking {
            contactsNotSavedOnSavedStateHandle()
            mockGetAllContactsResponse(Result.failure(RuntimeException()))

            viewModel.refreshContacts()

            isErrorState(state = viewModel.uiState.value)
            verify { savedStateHandle.set(CONTACTS_KEY, null) }
        }

    private fun isLoadingState(state: ContactsListUiState) {
        val expected = ContactsListUiState(isLoading = true)
        assertEquals(expected, state)
    }

    private fun isSuccessState(
        state: ContactsListUiState,
        @Suppress("SameParameterValue") contacts: List<ContactItemUiState>,
    ) {
        val expected = ContactsListUiState(contacts = contacts, scrollIsEnabled = true)
        assertEquals(expected, state)
    }

    private fun isErrorState(state: ContactsListUiState) {
        assertNotNull(state.error)
        assertEquals(ContactsListUiState(), state.copy(error = null))
        state.error.onClickTryAgain.invoke()
        verify(exactly = 2) { viewModel.refreshContacts() }
    }

    private fun contactsNotSavedOnSavedStateHandle() {
        every { savedStateHandle.get<List<ContactItemUiState>>(CONTACTS_KEY) } returns null
    }

    private fun mockGetAllContactsResponse(response: Result<List<Contact>>) {
        coEvery { getAllContacts() } returns response
    }
}

private const val CONTACTS_KEY = "contacts"
private val CONTACTS_LIST_MOCK = listOf(
    Contact(id = 0, "imgUrl", "fullName", "username")
)
private val CONTACT_LIST_ITEM_UI_STATE_MOCK = listOf(
    ContactItemUiState(id = 0, "imgUrl", "fullName", "username")
)