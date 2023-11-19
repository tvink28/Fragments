package com.tvink28.fragments.task_two

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.tvink28.fragments.R

class ListUsersAdapter(private val itemClickListener: (User) -> Unit) :
    ListAdapter<User, ListUsersAdapter.ListUsersViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListUsersViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ListUsersViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListUsersViewHolder, position: Int) {
        holder.bind(getItem(position), itemClickListener)
    }

    class ListUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val photoView: ImageView = itemView.findViewById(R.id.photo)
        private val nameView: TextView = itemView.findViewById(R.id.name)
        private val phoneView: TextView = itemView.findViewById(R.id.phone)

        fun bind(user: User, itemClickListener: (User) -> Unit) {

            val firstName = user.firstName
            val lastName = user.lastName
            val phone = user.phoneNumber

            with(itemView.context) {
                photoView.load(user.photo) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(10f))
                }
                nameView.text = getString(R.string.contact_name, firstName, lastName)
                phoneView.text = getString(R.string.contact_phone, phone)
            }
            itemView.setOnClickListener { itemClickListener(user) }
        }
    }
}

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: User, newItem: User) = oldItem == newItem
}