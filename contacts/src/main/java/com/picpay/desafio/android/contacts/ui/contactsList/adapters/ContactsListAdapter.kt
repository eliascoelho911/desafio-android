package com.picpay.desafio.android.contacts.ui.contactsList.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.contacts.R
import com.picpay.desafio.android.contacts.databinding.ListItemUserBinding
import com.picpay.desafio.android.contacts.ui.contactsList.ContactItemUiState
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

internal class ContactsListAdapter :
    ListAdapter<ContactItemUiState, ContactViewHolder>(ContactDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ListItemUserBinding.inflate(layoutInflater, parent, false)
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

internal class ContactViewHolder(private val binding: ListItemUserBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ContactItemUiState) {
        with(binding) {
            fullName.text = item.fullName
            username.text = binding.root.context.getString(R.string.username, item.username)
            bindPicture(item)
            setupAccessibility(item)
        }
    }

    private fun setupAccessibility(item: ContactItemUiState) {
        binding.root.contentDescription = binding.root.context.getString(
            R.string.contacts_list_item_accessibility,
            item.username,
            item.fullName
        )
    }

    private fun bindPicture(item: ContactItemUiState) {
        Picasso.get()
            .load(item.imgUrl)
            .fit()
            .centerCrop()
            .placeholder(R.drawable.ic_round_account_circle)
            .transform(CropCircleTransformation())
            .into(binding.picture)
    }
}

private class ContactDiffUtil : DiffUtil.ItemCallback<ContactItemUiState>() {
    override fun areItemsTheSame(
        oldItem: ContactItemUiState,
        newItem: ContactItemUiState,
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: ContactItemUiState,
        newItem: ContactItemUiState,
    ) = oldItem == newItem
}