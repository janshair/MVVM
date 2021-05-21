package com.tapdevs.users.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapdevs.core.network.Status
import com.tapdevs.core.ui.activity.BaseActivity
import com.tapdevs.core.ui.fragments.BaseFragment
import com.tapdevs.users.R
import com.tapdevs.users.data.model.User
import com.tapdevs.users.databinding.FragmentUsersBinding
import com.tapdevs.users.ui.adapter.UsersAdapter
import com.tapdevs.users.viewmodel.UsersViewModel
import org.koin.android.ext.android.inject
private const val EXTRA_PROFILE_URL = "EXTRA_PROFILE_URL"
class UsersFragment : BaseFragment() {

    private val usersViewModel: UsersViewModel by inject()

    private lateinit var usersAdapter: UsersAdapter

    private lateinit var navController: NavController

    private lateinit var fragmentUsersBinding: FragmentUsersBinding

    override fun getErrorView(): View = fragmentUsersBinding.usersError.root

    override fun getErrorMessageTextView(): TextView = fragmentUsersBinding.usersError.tvError

    override fun getSuccessView(): View = fragmentUsersBinding.usersSuccess.root

    override fun getWaitingView(): View = fragmentUsersBinding.usersWait.root

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentUsersBinding = FragmentUsersBinding.inflate(inflater, container, false)
        return fragmentUsersBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        usersViewModel.getAllUsers().observe(
            viewLifecycleOwner,
            {
                when (it.status) {
                    Status.LOADING -> showProgress()
                    Status.SUCCESS -> {
                        showSuccessResponse()
                        loadData(it.data)
                    }
                    Status.ERROR -> showError(it.message ?: getString(R.string.error_loading_data))
                }
            }
        )
        usersViewModel.fetchAllUsers()
    }

    private fun loadData(users: List<User>?) {
        users?.let {
            loadUsersInRecyclerView(it)
        } ?: showError(getString(R.string.error_loading_data))
    }

    private fun loadUsersInRecyclerView(users: List<User>) {
        usersAdapter = UsersAdapter(activity as BaseActivity, users)
        fragmentUsersBinding.usersSuccess.rvUsers.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = usersAdapter
        }
        usersAdapter.userClicked = {
            // Go to User profile
            val bundle = bundleOf(
                EXTRA_PROFILE_URL to it.htmlUrl
            )
            if (::navController.isInitialized) { // should be true always
                navController.navigate(R.id.action_usersFragment_to_userDetailsFragment, bundle)
            }
        }
    }
}
