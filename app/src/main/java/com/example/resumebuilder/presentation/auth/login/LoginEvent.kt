package com.example.resumebuilder.presentation.auth.login

sealed interface LoginEvent{
    data class EmailChanged(
        val email:String
    ):LoginEvent
    data class PasswordChanged(
        val password:String
    ):LoginEvent
    data object LoginClicked:LoginEvent
    data object NavigateToSignup:LoginEvent
}