package com.example.resumebuilder.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.resumebuilder.data.local.db.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)
    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUser(email: String): UserEntity?
    @Query("SELECT * FROM users WHERE email=:email AND password=:password")
    suspend fun login(
        email: String,
        password: String
    ): UserEntity?
}