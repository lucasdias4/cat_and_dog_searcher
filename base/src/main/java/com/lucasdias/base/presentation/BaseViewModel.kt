package com.lucasdias.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasdias.core.resource.Resource
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<T : Any?>(
    private val coroutineContext: CoroutineDispatcher
) : ViewModel() {

    val responseLiveData: LiveData<Resource<T>> by lazy { _responseLiveData }
    private var isInitialRequest = true
    private var hasNetworkConnectivity = true
    private val _responseLiveData: MutableLiveData<Resource<T>> by lazy { MutableLiveData<Resource<T>>() }
    private var isLoading = false

    abstract suspend fun request(): Resource<T>

    fun executeRequest() {
        if (hasNetworkConnectivity.not()) return
        setLoadingStatus()

        launch {
            val response = request()
            handleRequest(response)
        }
    }

    fun ViewModel.launch(
        context: CoroutineContext = coroutineContext,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(context, block = block)

    fun isLoading() = isLoading

    fun isNotLoading() = isLoading.not()

    fun isInitialRequest() = isInitialRequest

    fun isNotInitialRequest() = isInitialRequest.not()

    private fun setLoadingStatus() {
        handleRequest(Resource.Loading())
    }

    private fun handleRequest(resource: Resource<T>) {
        when (resource) {
            is Resource.Success -> {
                isLoading = false
                isInitialRequest = false
                _responseLiveData.postValue(resource)
            }
            is Resource.SuccessWithoutContent -> {
                isLoading = false
                _responseLiveData.postValue(resource)
            }
            is Resource.Error -> {
                isLoading = false
                _responseLiveData.postValue(resource)
            }
            is Resource.Loading -> {
                isLoading = true
                _responseLiveData.postValue(resource)
            }
        }
    }

    fun updateConnectivityStatus(hasNetworkConnectivity: Boolean) {
        this.hasNetworkConnectivity = hasNetworkConnectivity
    }
}
