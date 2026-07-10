package com.example.resumebuilder.domain.model

import kotlinx.serialization.Serializable

// domain/model/User.kt

data class User(


    val id: Long,
    val name: String,
    val email: String,
    val password: String="",
    val token: String = "" // Future use ke liye
)