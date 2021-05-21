package com.tapdevs.users.data.api

import retrofit2.http.GET

interface UsersApi {

    @GET("users")
    suspend fun getUsers()
}