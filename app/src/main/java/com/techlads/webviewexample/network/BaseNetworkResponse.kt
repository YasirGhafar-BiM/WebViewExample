package com.techlads.webviewexample

import com.techlads.webviewexample.network.ApiClient
import com.techlads.webviewexample.network.BaseResponse
import com.techlads.webviewexample.utils.ErrorUtils
import com.techlads.webviewexample.utils.Resource
import retrofit2.Response

open class BaseNetworkSource {

    protected suspend fun <T : BaseResponse> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Resource<T> {
        return try {
            val result = request.invoke()

            if (result.isSuccessful) {
                val body = result.body()
                success(body)
                //Resource.success<T>(result.body()) // TODO : Dont use this method, use success method
            } else {
                val client = ApiClient.retrofit;
                val err = ErrorUtils.parseError(result, client!!)
                Resource.error(err.message.toString(), null) // TODO : Dont use this method, use error method
                error(err.message.toString())
            }
        } catch (ex: Throwable) {
            error(ex.message ?: "Unexpected error")
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.error(message, null)
    }

    private fun <T> success(data: T?): Resource<T> {
        return Resource.success(data)
    }
}