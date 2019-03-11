package com.pexe.encryption.repository.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pexe.encryption.model.User

@Dao
interface UserDAO {

    @Query("select * from User")
    fun getUsers(): List<User>

    @Query("select exists(select * from User where userName = :userName)")
    fun existsUserName(userName: String): Boolean

    @Insert
    fun putUser(user: User)

    @Delete
    fun deleteUser(user: User)
}