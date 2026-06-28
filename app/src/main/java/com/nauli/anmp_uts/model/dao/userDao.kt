package com.nauli.anmp_uts.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nauli.anmp_uts.model.User

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE username = :username AND password = :password LIMIT 1")
    fun login(
        username: String,
        password: String
    ): User?

    @Query("SELECT COUNT(*) FROM user")
    fun getTotalUser(): Int
}