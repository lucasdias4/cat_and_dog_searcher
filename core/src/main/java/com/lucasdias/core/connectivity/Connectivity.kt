package com.lucasdias.core.connectivity

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.lucasdias.core.livedata.SingleLiveEvent

class Connectivity(context: Context) {

    private val _liveData = SingleLiveEvent<Boolean>()
    private var isDeviceJustStarted = true
    private var isConnected = true

    init {
        registerNetworkCallback(connectivityManager = getConnectivityManager(context))
    }

    fun getLiveData(): SingleLiveEvent<Boolean> = _liveData

    private fun getConnectivityManager(context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
    }

    private fun registerNetworkCallback(connectivityManager: ConnectivityManager) {
        connectivityManager.registerDefaultNetworkCallback(
            ConnectivityCallback { isConnected: Boolean ->
                notifyConnectedState(isConnected)
            }
        )
    }

    private fun notifyConnectedState(isConnected: Boolean) {
        if (this.isConnected == isConnected) return

        this.isConnected = isConnected
        isDeviceJustStarted = false
        _liveData.postValue(isConnected)
    }

    class ConnectivityCallback(val notifyConnectedState: (Boolean) -> Unit) :
        ConnectivityManager.NetworkCallback() {

        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            notifyConnectedState(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            notifyConnectedState(false)
        }
    }
}
