package com.example.userlocation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userlocation.databinding.RvLiistItemBinding
import com.example.userlocation.model.UserModelItem

class UserItemRecyclerView() : RecyclerView.Adapter<UserItemRecyclerView.UserItemViewHolder>()
{

    private val userModelItem=mutableListOf<UserModelItem>()
    fun addUserData(list:List<UserModelItem>)
    {
        userModelItem.clear()
        userModelItem.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent:ViewGroup, viewType:Int):UserItemViewHolder
    {
        return UserItemViewHolder(DataBindingUtil
            .inflate(LayoutInflater.from(parent.context),R.layout.rv_liist_item,parent,false))
    }

    override fun getItemCount():Int
    {
        return userModelItem.size
    }

    override fun onBindViewHolder(holder:UserItemViewHolder, position:Int)
    {
        holder.bind(userModelItem[position])

    }

    inner class UserItemViewHolder(val binding:RvLiistItemBinding):RecyclerView.ViewHolder(binding.root)
    {
        fun bind(userModelItem:UserModelItem)
        {
            binding.setVariable(BR.userModelItem,userModelItem)
        }

    }

}