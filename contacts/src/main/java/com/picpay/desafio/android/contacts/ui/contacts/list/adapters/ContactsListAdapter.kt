package com.picpay.desafio.android.contacts.ui.contacts.list.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.databinding.ListItemUserBinding
import com.picpay.desafio.android.contacts.ui.contacts.list.ContactItemUiState

internal class ContactsListAdapter :
    ListAdapter<ContactItemUiState, ContactViewHolder>(ContactDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ListItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class ContactViewHolder(private val binding: ListItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ContactItemUiState) {
        with(binding) {
            fullName.text = item.fullName
            username.text = binding.root.context.getString(R.string.username, item.username)
        }
    }
}

internal class ContactDiffUtil : DiffUtil.ItemCallback<ContactItemUiState>() {
    override fun areItemsTheSame(
        oldItem: ContactItemUiState,
        newItem: ContactItemUiState,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ContactItemUiState,
        newItem: ContactItemUiState,
    ) = oldItem == newItem
}