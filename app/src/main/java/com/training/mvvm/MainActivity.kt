package com.training.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.training.mvvm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao = UserDatabase.getDatabase(application).userDao
        val repo = UserRepo(dao)
        val factory = UserViewModelFactory(repo)
        userViewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this
        initRecycleView()

    }

    private fun initRecycleView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        displayuserList()
    }

    private fun displayuserList(){
        userViewModel.user
            .observe(this, Observer {
            Log.i("my-tag",it.toString())
                binding.recyclerview.adapter = RecycleViewAdapter(it,{selectedUser:User->listUserClicked(selectedUser)})
        })

    }

    private fun listUserClicked(user: User) {
        Toast.makeText(this,"select name is ${user.name}",Toast.LENGTH_LONG).show()
        userViewModel.initUpdateAndDelete(user)
    }
}