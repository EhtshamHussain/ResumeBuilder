package com.example.resumebuilder.presentation.auth.signup



data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)