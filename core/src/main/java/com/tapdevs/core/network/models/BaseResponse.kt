package com.tapdevs.core.network.models

import com.google.gson.annotations.SerializedName

data class ErrorResponse (

    @SerializedName(value = "message")
    var message: String? = null,

    @SerializedName("documentation_url")
    var documentationUrl: String? = null
)
