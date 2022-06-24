package com.picpay.desafio.android.contacts.ui.contacts.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.picpay.desafio.android.contacts.databinding.ContactsListFragmentBinding
import com.picpay.desafio.android.contacts.ui.contacts.list.adapters.ContactsListAdapter
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
                    binding.loadingView.isVisible = uiState.isLoading
                    contactsListAdapter.submitList(uiState.contacts)
                }
            }
        }
    }
}