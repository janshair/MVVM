package com.tapdevs.users.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.tapdevs.core.ui.fragments.BaseFragment
import com.tapdevs.users.databinding.FragmentUsersBinding

class UsersFragment: BaseFragment() {

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
        
    }
}