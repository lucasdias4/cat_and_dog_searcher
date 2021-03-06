package com.lucasdias.core.resource

import java.lang.Exception

@Suppress("UNCHECKED_CAST")
sealed class Resource<T> {
    abstract fun value(): T?
    abstract fun error(): Exception?

    fun isAnError() = error() != null

    data class Success<T>(private val value: T) : Resource<T>() {
        override fun value() = value
        override fun error() = null
    }

    class SuccessWithoutContent<T> : Resource<T>() {
        override fun value() = null
        override fun error() = null
    }

    data class Error<T>(private val exception: Exception) : Resource<T>() {
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

        suspend fun <V> listOf(suspendFunction: suspend () -> List<V>): Resource<List<V>> = try {
            val value = suspendFunction()
            value.getCorrectSuccessResourceType()
        } catch (exception: Exception) {
            Error(exception)
        }
    }
}
