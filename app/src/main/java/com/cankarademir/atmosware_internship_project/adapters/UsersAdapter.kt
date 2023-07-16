package com.cankarademir.atmosware_internship_project.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView

import com.cankarademir.atmosware_internship_project.R
import com.cankarademir.atmosware_internship_project.models.Users
import com.cankarademir.atmosware_internship_project.ui.user.UserFragmentDirections

class UsersAdapter(private val dataList: List<Users>) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    class ViewHolder(itemView: View,private val dataList: List<Users>) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.title)
        val usernameTextView: TextView = itemView.findViewById(R.id.username)
        val emailTextView: TextView = itemView.findViewById(R.id.email)

        init {
            itemView.setOnClickListener {
                val data = dataList[adapterPosition]
                val action = UserFragmentDirections.actionCommentsFragmentToDetailFragment(data)
                val navController = Navigation.findNavController(itemView)
                navController.navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item_users, parent, false)
        return ViewHolder(itemView,dataList)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = dataList[position]
        holder.nameTextView.text = data.name
        holder.emailTextView.text = data.email
        holder.usernameTextView.text = data.username
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}