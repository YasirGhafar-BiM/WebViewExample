package com.techlads.webviewexample.model

import com.google.gson.annotations.SerializedName
import com.techlads.webviewexample.network.BaseResponse
import java.io.Serializable

open class Asset (
    @SerializedName("animationCount")
    var animationCount: Int,
    @SerializedName("archives")
    var archives : Archive,
    @SerializedName("categories")
    var categories : Any,
    @SerializedName("commentCount")
    var commentCount : Int,
    @SerializedName("createdAt")
    var createdAt : String,
    @SerializedName("descriptionField")
    var descriptionField : String,
    @SerializedName("embedUrl")
    var embedUrl : String,
    @SerializedName("faceCount")
    var faceCount : Int,
    @SerializedName("isAgeRestricted")
    var isAgeRestricted : Boolean,
    @SerializedName("isDownloadable")
    var isDownloadable : Boolean,
    @SerializedName("likeCount")
    var likeCount : Int,
    @SerializedName("name")
    var name : String,
    @SerializedName("publishedAt")
    var publishedAt : Any,
    @SerializedName("staffpickedAt")
    var staffpickedAt : Any,
    @SerializedName("tags")
    var tags : Any,
    @SerializedName("thumbnails")
    var thumbnails : Thumbnail,
    @SerializedName("uid")
    var uid : String,
    @SerializedName("uri")
    var uri : String,
    @SerializedName("user")
    var user : User,
    @SerializedName("vertexCount")
    var vertexCount : Int,
    @SerializedName("viewCount")
    var viewCount : Int,
    @SerializedName("viewerUrl")
    var viewerUrl : String

        ): BaseResponse(), Serializable