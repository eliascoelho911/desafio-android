package com.picpay.desafio.android.core.network

sealed class UseCaseResult<T> {
    data class Success<T>(val data: T) : UseCaseResult<T>()
    data class Error<T>(val message: String) : UseCaseResult<T>()
}

inline fun <T> UseCaseResult<T>.fold(
    onSuccess: (T) -> Unit,
    onError: (message: String) -> Unit,
) {
    when (this) {
        is UseCaseResult.Success -> onSuccess(data)
        is UseCaseResult.Error -> onError(message)
    }
}