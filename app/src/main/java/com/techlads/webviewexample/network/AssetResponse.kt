package com.techlads.webviewexample.network

import com.google.gson.annotations.SerializedName
import com.techlads.webviewexample.model.Asset

open class AssetResponse(

    @SerializedName("results")
    var results: List<Asset>? = null
): BaseResponse()