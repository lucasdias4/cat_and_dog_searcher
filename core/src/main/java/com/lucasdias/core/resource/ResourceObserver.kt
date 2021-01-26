package com.lucasdias.core.resource

import androidx.lifecycle.Observer

class ResourceObserver<T>(
    private var loading: (() -> Unit)? = null,
    private var success: ((T?) -> Unit)? = null,
    private var error: ((Throwable?) -> Unit)? = null
) : Observer<Resource<T>> {

    override fun onChanged(resource: Resource<T>?) {
        when (resource) {
            is Resource.Loading -> loading?.invoke()
            is Resource.Success -> success?.invoke(resource.value)
            is Resource.Error -> error?.invoke(resource.exception)
        }
    }
}
