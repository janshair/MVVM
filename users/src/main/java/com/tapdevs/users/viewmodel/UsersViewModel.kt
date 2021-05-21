package com.tapdevs.users.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tapdevs.core.network.Resource
import com.tapdevs.users.data.model.User
import com.tapdevs.users.repository.UserRepository
import kotlinx.coroutines.launch

class UsersViewModel(private val usersRepository: UserRepository) : ViewModel() {
    private val allUsers: MutableLiveData<Resource<List<User>>> = MutableLiveData<Resource<List<User>>>()

    fun fetchAllUsers() {
        viewModelScope.launch {
            allUsers.postValue(Resource.loading(data = null))
            allUsers.postValue(usersRepository.getUsers())
        }
    }

    fun getAllUsers() = allUsers
}
