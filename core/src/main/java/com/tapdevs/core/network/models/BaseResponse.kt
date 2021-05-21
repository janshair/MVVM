package com.tapdevs.base.network.models

import com.google.gson.annotations.SerializedName

open class ErrorResponse {

    @SerializedName(value = "message")
    var message: String? = null

    @SerializedName("documentation_url")
    var documentationUrl: String? = null
}
