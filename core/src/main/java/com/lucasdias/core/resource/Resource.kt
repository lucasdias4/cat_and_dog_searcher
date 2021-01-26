package com.lucasdias.core.resource

import java.lang.Exception

@Suppress("UNCHECKED_CAST")
sealed class Resource<T> {
    abstract fun value(): T?
    abstract fun error(): Exception?

    data class Success<T>(val value: T) : Resource<T>() {
        override fun value() = value
        override fun error() = null
    }
    data class Error<T>(val exception: Exception) : Resource<T>() {
        override fun value() = null
        override fun error() = exception
    }
    class Loading<T> : Resource<T>() {
        override fun value() = null
        override fun error() = null
    }

    companion object {
        suspend fun <V : Any> of(suspendFunction: suspend () -> V): Resource<V> = try {
            val value = suspendFunction()
            Success(value)
        } catch (exception: Exception) {
            Error(exception)
        }
    }
}
