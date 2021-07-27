package com.techlads.webviewexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Avatar (
    @SerializedName("images")
    var images : ArrayList<Image>? = null,
    @SerializedName("uri")
    var uri : String,
    ): Serializable