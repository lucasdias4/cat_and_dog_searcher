package com.lucasdias.core.resource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import java.lang.Exception

/**
 * observe all
 */
fun <T> LiveData<Resource<T>>.observe(
    owner: LifecycleOwner,
    loading: (() -> Unit),
    success: ((T?) -> Unit),
    successWithoutContent: (() -> Unit),
    error: ((Throwable?) -> Unit)
) {
    val observer = ResourceObserver<T>(
        loading = loading,
        success = success,
        successWithoutContent = successWithoutContent,
        error = error
    )
    observe(owner, observer)
}

/**
 * observe success only
 */
fun <T> LiveData<Resource<T>>.observe(owner: LifecycleOwner, success: (T?) -> Unit) {
    val observer = ResourceObserver<T>(success = success)
    observe(owner, observer)
}

fun <T> List<Resource<List<T>>>.mergeResources(): Resource<List<T>> {
    val resourceList = mutableListOf<T>()

    this.forEach { resource ->
        if (resource.isAnError()) return Resource.Error(resource.error() ?: Exception())

        resource.value()?.let {
            resourceList.addAll(it)
        }
    }

    return if (resourceList.isEmpty()) Resource.SuccessWithoutContent()
    else Resource.Success(resourceList)
}

fun <E> List<E>.getCorrectTypeOSuccessResource(): Resource<List<E>> {
    return if (this.isEmpty()) Resource.SuccessWithoutContent()
    else Resource.Success(this)
}
