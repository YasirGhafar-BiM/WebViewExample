package com.techlads.webviewexample

import com.techlads.webviewexample.network.AssetResponse
import com.techlads.webviewexample.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AssetsRepository {

    private val networkSource = AssetsNetworkSource()
    suspend fun getModels(): Flow<Resource<AssetResponse>> {
        return flow {
            emit(Resource.loading())
            emit(networkSource.getAssets())
        }.flowOn(Dispatchers.IO)
    }
}