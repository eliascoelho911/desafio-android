package com.picpay.desafio.android.contacts.data.converters

import com.picpay.desafio.android.contacts.data.dtos.ContactDTO
import com.picpay.desafio.android.contacts.domain.entities.Contact
import com.picpay.desafio.android.core.test.BaseTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ContactConvertersKtTest: BaseTest() {
    @Test
    fun testConvertContactDTOToEntity() {
        val dto = ContactDTO(id = 0, imgUrl = "imgUrl", fullName = "fullName", username = "username")
        val expected = Contact(id = 0, imgUrl = "imgUrl", fullName = "fullName", username = "username")

        assertEquals(expected, dto.toEntity())
    }
}