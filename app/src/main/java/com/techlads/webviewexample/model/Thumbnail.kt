package com.techlads.webviewexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class Thumbnail (
    @SerializedName("images")
    var images : ArrayList<Image>? = null
): Serializable