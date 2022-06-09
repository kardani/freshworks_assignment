package com.kardani.masgiphy.core

sealed class DataState<out T> {
    data class Success<out T>(val value: T): DataState<T>()
    data class Error(val error: String? = null): DataState<Nothing>()
    object Loading: DataState<Nothing>()
}