package com.example.daggerhilt.ui.mian

import android.util.Size
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerhilt.R
import com.example.daggerhilt.data.model.User
import com.example.daggerhilt.databinding.RvUserListBinding

class MainAdater(private val users:ArrayList<User>):RecyclerView.Adapter<DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

//       return DataViewHolder(LayoutInflater.from(parent.context).inflate(
//            R.layout.rv_user_list,parent,
//            false
//        )
//       )


        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = RvUserListBinding.inflate(layoutInflater)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount():Int = users.size

    fun addData(list: List<User>){
        users.add(list as User)
    }
}
