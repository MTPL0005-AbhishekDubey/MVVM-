package com.training.mvvm

import androidx.lifecycle.LiveData

class UserRepo(private val userDAO: UserDAO) {

    val user = userDAO.getUser()

    suspend fun insertUser(user: User) {

        return userDAO.insertUser(user)

    }

    suspend fun deleteUser(user: User) {
        return userDAO.deleteUser(user)
    }

    suspend fun deleteAll(): Int {
        return userDAO.deleteAll()
    }

    suspend fun updateUser(user: User) {
        return userDAO.deleteUser(user)
    }


}