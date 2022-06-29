package com.picpay.desafio.android.core.commons.data

/**
 * Todo: Testar se get returna null quando não encontrar dado
 * Todo: Testar se get returna dado quando existir
 * Todo: Testar se set seta dado com sucesso
 * Todo: Testar se remove remove dado com sucesso
 */
open class LocalDataSource<in Key : Any, T> {
    private val data: MutableMap<Key, T> = mutableMapOf()

    fun get(key: Key): T? = data[key]

    fun set(key: Key, value: T) {
        data[key] = value
    }

    fun remove(key: Key) {
        data.remove(key)
    }
}