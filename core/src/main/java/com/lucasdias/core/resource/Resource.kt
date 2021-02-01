package com.lucasdias.core.resource

@Suppress("UNCHECKED_CAST")
sealed class Resource<T> {
    abstract fun value(): T?
    abstract fun error(): Throwable?

    fun isAnError() = error() != null

    data class Success<T>(private val value: T) : Resource<T>() {
        override fun value() = value
        override fun error() = null
    }

    class SuccessWithoutContent<T> : Resource<T>() {
        override fun value() = null
        override fun error() = null
    }

    data class Error<T>(private val throwable: Throwable) : Resource<T>() {
        override fun value() = null
        override fun error() = throwable
    }

    class Loading<T> : Resource<T>() {
        override fun value() = null
        override fun error() = null
    }
}
