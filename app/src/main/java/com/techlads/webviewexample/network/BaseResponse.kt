package com.techlads.webviewexample.network

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class BaseResponse(@field:SerializedName("message")
                        val message: String? = "",

                        @field:SerializedName("status")
                        val status: Int? = 0): Serializable