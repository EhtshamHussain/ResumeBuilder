package com.example.resumebuilder.presentation.auth.signup

import com.example.resumebuilder.presentation.shared.presentation.base.BaseState

data class SignUpState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    override val isLoading: Boolean = false,
    val error: String? = null
) : BaseState