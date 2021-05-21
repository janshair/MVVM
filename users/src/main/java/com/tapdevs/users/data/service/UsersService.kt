package com.tapdevs.users.data.service

import com.tapdevs.users.data.api.UsersApi

class UsersService(private val usersApi: UsersApi) {
    suspend fun getUsers() = usersApi.getUsers()
}
