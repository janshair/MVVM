package com.tapdevs.users.injection

import com.tapdevs.users.data.api.UsersApi
import com.tapdevs.users.data.service.UsersService
import com.tapdevs.users.repository.UserRepository
import com.tapdevs.users.viewmodel.UsersViewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val usersModule = module {
    factory {
        UsersViewModel(get())
    }
    factory {
        val retrofit: Retrofit = get()
        retrofit.create(UsersApi::class.java)
    }
    factory {
        UsersService(get())
    }
    factory {
        UserRepository(get(), get())
    }
}
