package com.example.resumebuilder.domain.model
data class User(
    val id: Long,
    val name: String,
    val email: String,
    val password: String="",
)