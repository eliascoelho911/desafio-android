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
import androidx.recyclerview.widget.RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_NO_SCROLL
import com.google.android.material.appbar.AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL
import com.picpay.desafio.android.contacts.databinding.ContactsListFragmentBinding
import com.picpay.desafio.android.contacts.ui.contactsList.adapters.ContactsListAdapter
import com.picpay.desafio.android.core.commons.isExpanded
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.stateViewModel

private const val AppBarIsExpandedKey = "AppBarIsExpanded"

internal class ContactsListFragment : Fragment() {

    private val contactsSharedViewModel: ContactsListViewModel by stateViewModel()
    private lateinit var binding: ContactsListFragmentBinding
    private val contactsListAdapter by lazy { ContactsListAdapter() }
    private var appBarIsInitiallyExpanded: Boolean? = null

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

        getSavedState(savedInstanceState)
        setupViews()
        contactsSharedViewModel.refreshContacts()
        subscribeUi()
    }

    private fun getSavedState(savedInstanceState: Bundle?) {
        appBarIsInitiallyExpanded = savedInstanceState?.getBoolean(AppBarIsExpandedKey) ?: true
    }

    private fun setupViews() {
        setupContactsList()
    }

    private fun setupContactsList() {
        binding.contactsList.adapter = contactsListAdapter.apply {
            stateRestorationPolicy = PREVENT_WHEN_EMPTY
        }
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
            setupAppBarBehavior(uiState.scrollIsEnabled)
            loadingView.isVisible = uiState.isLoading
            errorView.isVisible = uiState.error != null
            uiState.error?.let { error ->
                errorView.setMessage(getString(error.messageRes))
                errorView.onClickTryAgain = error.onClickTryAgain
            }
        }
        contactsListAdapter.submitList(uiState.contacts)
    }

    private fun setupAppBarBehavior(scrollIsEnabled: Boolean) {
        binding.collapsingToolbarLayout.updateLayoutParams<AppBarLayout.LayoutParams> {
            if (scrollIsEnabled) {
                scrollFlags = SCROLL_FLAG_SCROLL or SCROLL_FLAG_EXIT_UNTIL_COLLAPSED
                restoreAppBarState()
            } else {
                scrollFlags = SCROLL_FLAG_NO_SCROLL
            }
        }
    }

    private fun restoreAppBarState() {
        appBarIsInitiallyExpanded?.let {
            binding.appBarLayout.setExpanded(it)
        }
        appBarIsInitiallyExpanded = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(AppBarIsExpandedKey, binding.appBarLayout.isExpanded())
    }
}