package com.picpay.desafio.android.contacts.ui.contactsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.appbar.AppBarLayout
import com.picpay.desafio.android.contacts.databinding.ContactsListFragmentBinding
import com.picpay.desafio.android.contacts.ui.contactsList.adapters.ContactsListAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class ContactsListFragment : Fragment() {

    private val contactsSharedViewModel: ContactsListViewModel by viewModel()
    private lateinit var binding: ContactsListFragmentBinding
    private val contactsListAdapter by lazy { ContactsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = ContactsListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupContactsList()
        contactsSharedViewModel.refreshContacts()
        subscribeUi()
    }

    private fun setupContactsList() {
        binding.contactsList.adapter = contactsListAdapter
    }

    private fun subscribeUi() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactsSharedViewModel.uiState.collect { uiState ->
                    bind(uiState)
                }
            }
        }
    }

    private fun bind(uiState: ContactsListUiState) {
        with(binding) {
            updateCollapsingToolbarScrollBehavior(uiState.scrollIsEnabled)
            loadingView.isVisible = uiState.isLoading
            errorView.isVisible = uiState.error != null
            uiState.error?.let { error ->
                errorView.setMessage(error.message)
                errorView.onClickTryAgain = error.onClickTryAgain
            }
        }
        contactsListAdapter.submitList(uiState.contacts)
    }

    private fun ContactsListFragmentBinding.updateCollapsingToolbarScrollBehavior(
        scrollIsEnabled: Boolean,
    ) {
        collapsingToolbarLayout.updateLayoutParams<AppBarLayout.LayoutParams> {
            scrollFlags = if (scrollIsEnabled) {
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or
                        AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
            } else AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
        }
    }
}