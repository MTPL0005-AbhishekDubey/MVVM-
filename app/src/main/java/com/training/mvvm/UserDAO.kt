package com.training.mvvm

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user : User)

    @Update
    suspend fun updateUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getUser() : LiveData<List<User>>
}