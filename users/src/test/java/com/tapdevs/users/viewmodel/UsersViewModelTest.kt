package com.tapdevs.users.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.tapdevs.core.network.Resource
import com.tapdevs.users.data.model.User
import com.tapdevs.users.repository.UserRepository
import com.tapdevs.users.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UsersViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var usersRepository: UserRepository

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<List<User>>>

    private lateinit var viewModel: UsersViewModel

    @Before
    fun setUp() {
        viewModel = UsersViewModel(usersRepository)
    }

    @Test
    fun `given server response 200 when fetch should return success`() {
        startFetchingUserResult(Resource.success(data = emptyList()))
    }

    @Test
    fun `given server response error when fetch should return error`() {
        val errorMessage = "Dummy Error Message"
        startFetchingUserResult(Resource.error(data = null, RuntimeException(errorMessage).toString()))
    }

    private fun startFetchingUserResult(data: Resource<List<User>>) {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(data).`when`(usersRepository).getUsers()
            viewModel.fetchAllUsers()
            viewModel.getAllUsers().observeForever(apiUsersObserver)

            Mockito.verify(usersRepository).getUsers()
            Mockito.verify(apiUsersObserver).onChanged(data)
            viewModel.getAllUsers().removeObserver(apiUsersObserver)
        }
    }
}