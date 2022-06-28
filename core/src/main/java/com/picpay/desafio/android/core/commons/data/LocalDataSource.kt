package com.picpay.desafio.android.core.commons.data

open class LocalDataSource<in Key : Any, T> {
    private val data: MutableMap<Key, T> = mutableMapOf()

    fun get(key: Key): T? = data[key]

    fun set(key: Key, value: T) {
        data[key] = value
    }

    fun remove(key: Key) {
        data.remove(key)
    }

    fun clear() {
        data.clear()
    }
}