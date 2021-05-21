package com.tapdevs.users.data.model.response

import com.tapdevs.base.network.models.ErrorResponse
import com.tapdevs.users.data.model.User

data class UsersResponse(
    val errorResponse: ErrorResponse,
    val users: List<User>
)
