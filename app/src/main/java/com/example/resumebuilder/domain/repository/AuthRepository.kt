package com.example.resumebuilder.domain.repository

import com.example.resumebuilder.data.local.entity.UserEntity
import com.example.resumebuilder.domain.model.User

interface AuthRepository {

    suspend fun login(
        email: String,
        password: String
    ): Result<User>

    suspend fun signup(name: String, email: String, password: String): Result<User>

    suspend fun isEmailExists(email: String): Boolean

//    suspend fun signup(
//        user: UserEntity
//    )
//
//    suspend fun isEmailExists(
//        email: String
//    ): Boolean
}