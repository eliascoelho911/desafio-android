package com.picpay.desafio.android.contacts.data.dtos

import com.google.gson.annotations.SerializedName

internal data class ContactDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("img") val imgUrl: String,
    @SerializedName("name") val fullName: String,
    @SerializedName("username") val username: String,
)