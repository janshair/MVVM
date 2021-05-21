package com.tapdevs.users.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tapdevs.core.ui.activity.BaseActivity
import com.tapdevs.users.data.model.User
import com.tapdevs.users.databinding.ListItemUserBinding

class UsersAdapter(private val activity: BaseActivity, private val users: List<User>) : RecyclerView.Adapter<UsersAdapter.UsersAdapterViewHolder>() {

    var userClicked: ((User) -> Unit)? = null

    class UsersAdapterViewHolder(val listItemUserBinding: ListItemUserBinding) : RecyclerView.ViewHolder(listItemUserBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapterViewHolder = UsersAdapterViewHolder(ListItemUserBinding.inflate(LayoutInflater.from(activity), parent, false))

    override fun onBindViewHolder(holder: UsersAdapterViewHolder, position: Int) {
        val user = users[position]
        holder.listItemUserBinding.apply {
            tvUserName.text = user.login
            cardUser.setOnClickListener {
                userClicked?.invoke(user)
            }
            Glide.with(activity).load(user.avatarUrl).into(ivUserImage)
        }
    }

    override fun getItemCount(): Int = users.count()
}
