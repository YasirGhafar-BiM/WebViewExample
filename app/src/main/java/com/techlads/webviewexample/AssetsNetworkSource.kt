package com.techlads.webviewexample

import com.techlads.webviewexample.network.ApiClient
import com.techlads.webviewexample.network.AssetResponse
import com.techlads.webviewexample.utils.Resource

class AssetsNetworkSource: BaseNetworkSource() {

    val authToken = "Token d570e22b43ae41f9b7a1d60f79e24850"
    suspend fun getAssets(): Resource<AssetResponse> {
        val service = ApiClient.apiService
        return getResponse(
            request = { service.getModels(authToken) },
            defaultErrorMessage = "Unable to get Assets"
        )
    }
}