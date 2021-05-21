package com.tapdevs.users.data.api

import com.tapdevs.users.data.model.User
import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    suspend fun getUsers(): List<User>
}
