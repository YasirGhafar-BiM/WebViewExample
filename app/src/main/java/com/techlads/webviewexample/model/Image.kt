package com.techlads.webviewexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Image(

    @SerializedName("height")
    var height : Int,
    @SerializedName("size")
    var size : Int,
    @SerializedName("uid")
    var uid : String,
    @SerializedName("url")
    var url : String,
    @SerializedName("width")
    var width : Int,
): Serializable