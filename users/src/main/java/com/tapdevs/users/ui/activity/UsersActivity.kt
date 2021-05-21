package com.tapdevs.users.ui.activity

import android.os.Bundle
import com.tapdevs.core.ui.activity.BaseActivity
import com.tapdevs.users.databinding.ActivityUsersBinding

class UsersActivity : BaseActivity() {
    lateinit var activityUsersBinding: ActivityUsersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityUsersBinding = ActivityUsersBinding.inflate(layoutInflater)
        val view = activityUsersBinding.root
        setContentView(view)
    }
}
