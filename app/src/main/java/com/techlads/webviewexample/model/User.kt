package com.techlads.webviewexample.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class User(

    @SerializedName("account")
    var account : String,
    @SerializedName("avatar")
    var avatar : Avatar,
    @SerializedName("displayName")
    var displayName : String,
    @SerializedName("profileUrl")
    var profileUrl : String,
    @SerializedName("uid")
    var uid : String,
    @SerializedName("uri")
    var uri : String,
    @SerializedName("username")
    var username : String
): Serializable