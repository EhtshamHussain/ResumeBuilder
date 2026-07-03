package com.example.resumebuilder.presentation.auth.signup


sealed interface SignUpEvent{

    data class NameChanged(val name:String):SignUpEvent

    data class EmailChanged(val email:String):SignUpEvent

    data class PasswordChanged(val password:String):SignUpEvent

    data object SignUpClicked:SignUpEvent

    data object NavigateToLogin:SignUpEvent

}