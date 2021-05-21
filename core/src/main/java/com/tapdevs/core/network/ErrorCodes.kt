package com.tapdevs.core.network

enum class ErrorCodes(val code: Int) {
    SOCKET_TIME_OUT(-1),
    BAD_REQUEST(403),
    UNAUTHORISED(401),
    NOT_FOUND(404)
}
