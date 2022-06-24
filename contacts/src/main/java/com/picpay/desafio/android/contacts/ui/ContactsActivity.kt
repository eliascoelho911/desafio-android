package com.picpay.desafio.android.contacts.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.picpay.desafio.android.contacts.databinding.ContactsActivityBinding

class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ContactsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContactsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    companion object {
        fun createIntent(context: Context) = Intent(context, ContactsActivity::class.java)
    }
}