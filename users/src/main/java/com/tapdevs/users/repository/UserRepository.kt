package com.tapdevs.users.repository

import com.tapdevs.base.network.ResponseHandler
import com.tapdevs.users.data.service.UsersService

class UserRepository(private val usersService: UsersService, private val responseHandler: ResponseHandler) {
    suspend fun getUsers() = try{
        val response = usersService.getUsers()
        responseHandler.handleSuccess(data = response)
    } catch (e: Exception) {
        responseHandler.handleException(e)
    }

}