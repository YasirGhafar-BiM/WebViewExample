package com.techlads.webviewexample.network

import com.techlads.webviewexample.model.Asset
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiService {

    @GET("me/search?type=models")
    suspend fun getModels(@Header("Authorization") authToken: String): Response<AssetResponse>

}