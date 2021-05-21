package com.tapdevs.core.network

import com.google.gson.Gson
import com.tapdevs.core.network.models.ErrorResponse
import retrofit2.HttpException
import java.net.SocketTimeoutException

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.error(data = null, message = getErrorBody(e))
            is SocketTimeoutException -> Resource.error(data = null, message = getErrorMessage(ErrorCodes.SOCKET_TIME_OUT.code))
            else -> Resource.error(data = null, message = getErrorMessage(Int.MAX_VALUE))
        }
    }

    private fun getErrorBody(exception: HttpException): String {
        exception.response()?.errorBody()?.let {
            val errorResponse: ErrorResponse = Gson().fromJson(it.string(), ErrorResponse::class.java)
            return errorResponse.message ?: getErrorMessage(exception.code())
        }

        return getErrorMessage(exception.code())
    }

    /**
     * This will be used for logging purpose only. These should not be displayed on UI.
     */
    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SOCKET_TIME_OUT.code -> "Timeout"
            ErrorCodes.UNAUTHORISED.code -> "Unauthorised"
            ErrorCodes.BAD_REQUEST.code -> "Bad Request"
            ErrorCodes.NOT_FOUND.code -> "Not Found"
            else -> "Something went wrong"
        }
    }
}
