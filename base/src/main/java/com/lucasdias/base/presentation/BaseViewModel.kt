package com.lucasdias.base.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lucasdias.core.connectivity.Connectivity
import com.lucasdias.core.resource.Resource
import com.lucasdias.core.scheduler.RequestSchedulers
import io.reactivex.rxjava3.core.Observable

abstract class BaseViewModel<T : Any?>(
    private val connectivity: Connectivity,
    private val requestSchedulers: RequestSchedulers
) : ViewModel() {

    val responseLiveData: LiveData<Resource<T>> by lazy { _responseLiveData }
    private val _responseLiveData: MutableLiveData<Resource<T>> by lazy { MutableLiveData<Resource<T>>() }
    private var isInitialRequest = true
    private var hasNetworkConnectivity = true
    private var isLoading = false

    abstract fun request(): Observable<Resource<T>>

    fun executeRequest() {
        if (connectivity.isNotConnected()) {
            setErrorStatus()
            return
        }

        request()
            .subscribeOn(requestSchedulers.subscribe)
            .observeOn(requestSchedulers.observe)
            .subscribe { handleRequest(it) }
    }

    fun getConnectivityLiveData() = connectivity.getLiveData()

    fun isLoading() = isLoading

    fun isNotLoading() = isLoading.not()

    fun isInitialRequest() = isInitialRequest

    fun isNotInitialRequest() = isInitialRequest.not()

    private fun setLoadingStatus() {
        handleRequest(Resource.Loading())
    }

    private fun setErrorStatus() {
        handleRequest(Resource.Error(Exception()))
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
}
