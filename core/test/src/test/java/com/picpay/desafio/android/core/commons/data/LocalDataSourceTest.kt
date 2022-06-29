package com.picpay.desafio.android.core.commons.data

import com.picpay.desafio.android.core.test.BaseTest
import io.mockk.spyk
import kotlin.test.assertEquals
import org.junit.Test

class LocalDataSourceTest : BaseTest() {
    private val localDataSource = spyk(LocalDataSource<String, String>())

    @Test
    fun givenDataExists_whenGet_shouldReturnData() {
        localDataSource.set(KEY, VALUE)

        val result = localDataSource.get(KEY)

        assertEquals(VALUE, result)
    }

    @Test
    fun givenDataNotExists_whenGet_shouldReturnNull() {
        val result = localDataSource.get(KEY)

        assertEquals(null, result)
    }

    @Test
    fun givenDataExists_whenRemove_shouldRemoveData() {
        localDataSource.set(KEY, VALUE)
        localDataSource.remove(KEY)

        val result = localDataSource.get(KEY)

        assertEquals(null, result)
    }
}

private const val KEY = "Key"
private const val VALUE = "Value"