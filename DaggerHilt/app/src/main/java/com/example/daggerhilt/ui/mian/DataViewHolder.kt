package com.example.daggerhilt.ui.mian

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.daggerhilt.data.model.User
import com.example.daggerhilt.databinding.RvUserListBinding

class DataViewHolder(val binding:RvUserListBinding):RecyclerView.ViewHolder(binding.root) {

    fun bind(user:User){
        binding.tvName.text = user.name
        binding.tvEmail.text = user.email
        Glide.with(binding.imgAvatar.context)
            .load(user.avatar)
            .into(binding.imgAvatar)
    }
}