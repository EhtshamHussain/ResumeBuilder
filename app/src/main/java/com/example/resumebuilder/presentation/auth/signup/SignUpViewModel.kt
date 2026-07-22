package com.example.resumebuilder.presentation.auth.signup

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.domain.repository.AuthRepository
import com.example.resumebuilder.presentation.bottombar.routes.BottomBarScreens
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class SignUpViewModel(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : BaseViewModel() {
    var state by mutableStateOf(SignUpState())
        private set
    private val PASSWORD_REGEX =
        Regex("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@#\$%^&+=!]).{8,}$")

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is SignUpEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is SignUpEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is SignUpEvent.SignUpClicked -> {
                signUpUser()
            }

            is SignUpEvent.NavigateToLogin -> {
                navigateBack()
            }
        }
    }
    private fun signUpUser() {
        viewModelScope.launch {

            val nameError = validateName(state.name)
            val emailError = validateEmail(state.email)
            val passwordError = validatePassword(state.password)

            if (nameError != null) {
                showError(nameError)
                return@launch
            }

            if (
                emailError != null
            ) {
                showError(emailError)
                return@launch
            }
            if (
                passwordError != null
            ) {
                showError(passwordError)
                return@launch
            }

            state = state.copy(isLoading = true)

            authRepository.signup(
                name = state.name,
                email = state.email,
                password = state.password
            ).onSuccess { user ->

                sessionManager.setLoggedIn(name = user.name, user.email)
                    Result.success(user)
                    state = state.copy(isLoading = false)
                    navigate(
                        NavigationAction.NavigateTo(
                            route = BottomBarScreens.Home,
                            clearBackStack = true
                        )
                    )
            }.onFailure {
                state = state.copy(
                    isLoading = false,
                    error = it.message
                )
            }
        }
    }
    fun validateName(name: String): String? {
        return when {
            name.isBlank() -> "Name is required"
            name.length < 3 -> "Name must be at least 3 characters"
            !name.matches(Regex("^[A-Za-z ]+$")) -> "Only letters are allowed"
            else -> null
        }
    }


    fun validateEmail(email: String): String? {
        val value = email.trim()
        return when {
            value.isBlank() ->
                "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() ->
                "Please enter a valid email address (e.g., name@example.com) to continue."
            else ->
                null
        }
    }

    fun validatePassword(password: String): String? {
        return when {
            password.isBlank() ->
                "Password is required"
            !PASSWORD_REGEX.matches(password) ->
                "Password must be at least 8 characters and include letters, numbers, and special characters."
            else ->
                null
        }
    }
}