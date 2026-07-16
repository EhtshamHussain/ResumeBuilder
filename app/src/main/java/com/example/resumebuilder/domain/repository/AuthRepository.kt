package com.example.resumebuilder.domain.repository

import com.example.resumebuilder.domain.model.User

interface AuthRepository {
    suspend fun login(
        name: String="",
        email: String,
        password: String
    ): Result<User>
    suspend fun signup(name: String, email: String, password: String): Result<User>
    suspend fun isEmailExists(email: String): Boolean
}