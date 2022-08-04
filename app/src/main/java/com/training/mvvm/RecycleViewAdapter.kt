package com.training.mvvm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.training.mvvm.databinding.ListUserBinding
import com.training.mvvm.generated.callback.OnClickListener

class RecycleViewAdapter(private val userList: List<User>, private val clickListener :(User)->Unit) :
    RecyclerView.Adapter<MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListUserBinding = DataBindingUtil.inflate(layoutInflater,R.layout.list_user,parent,false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userList[position], clickListener)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}

class MyViewHolder(private val binding: ListUserBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(user: User, clickListener :(User)->Unit) {
        binding.nameTextView.text = user.name
        binding.emailTextView.text = user.email
        binding.listUserLayout.setOnClickListener{
            clickListener(user)
        }
    }
}