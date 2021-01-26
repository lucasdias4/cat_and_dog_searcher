package com.lucasdias.core.resource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

/**
 * observe all
 */
fun <T> LiveData<Resource<T>>.observe(
    owner: LifecycleOwner,
    loading: (() -> Unit),
    success: ((T?) -> Unit),
    error: ((Throwable?) -> Unit)
) {
    val observer = ResourceObserver<T>(loading = loading, success = success, error = error)
    observe(owner, observer)
}

/**
 * observe success only
 */
fun <T> LiveData<Resource<T>>.observe(owner: LifecycleOwner, success: (T?) -> Unit) {
    val observer = ResourceObserver<T>(success = success)
    observe(owner, observer)
}
