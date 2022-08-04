package com.training.mvvm

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    val user = userRepo.user

    private var isUpdateOrDelete = false

    private lateinit var userToUpdateOrDelete : User

    var name = MutableLiveData<String>()

    var email = MutableLiveData<String>()

    val saveOrUpdatebtn = MutableLiveData<String>()

    val clearAllBtn = MutableLiveData<String>()

    init {
        saveOrUpdatebtn.value = "Add or Update"
        clearAllBtn.value = "Clear All"
    }

    fun saveOrUpdate() {
        if(isUpdateOrDelete) {
            userToUpdateOrDelete.name = name.value!!
            userToUpdateOrDelete.email = email.value!!
        }
        else {
            val inputName = name.value!!
            val inputEmail = email.value!!
            insert(User(0,inputName,inputEmail))
            name.value = ""
            email.value = ""
        }


    }

    fun clearAllOrDelete() {
        if(isUpdateOrDelete) {
            delete(userToUpdateOrDelete)
        }
        else {
            clearAll()
        }
    }

    private fun insert(user: User) {
        viewModelScope.launch {
            userRepo.insertUser(user)
        }
    }

    private fun delete(user: User) {
        viewModelScope.launch {
            userRepo.deleteUser(user)
            name.value = ""
            email.value = ""
            isUpdateOrDelete = false
            saveOrUpdatebtn.value = "Add"
            clearAllBtn.value = "Clear All"
        }
    }

    private fun clearAll() {
        viewModelScope.launch {
            userRepo.deleteAll()
        }
    }

    fun clearAllDelete(){
        if(isUpdateOrDelete) {
            delete(userToUpdateOrDelete)
        }
        else {
            clearAll()
        }
    }

    fun initUpdateAndDelete(user: User) {
        name.value = user.name
        email.value = user.email
        isUpdateOrDelete = true
        userToUpdateOrDelete = user
        saveOrUpdatebtn.value = "Update"
        clearAllBtn.value = "Delete"
    }


}