// presentation/auth/login/LoginViewModel.kt
package com.example.resumebuilder.presentation.auth.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.domain.repository.AuthRepository
import com.example.resumebuilder.presentation.auth.signup.SignUpState
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : BaseViewModel() {


    var state by mutableStateOf(LoginState())
        private set

//    private val _state = MutableStateFlow(LoginState())
//    val state: StateFlow<LoginState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is LoginEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is LoginEvent.LoginClicked -> {
                loginUser()
            }
            is LoginEvent.NavigateToSignup -> {
                navigate(NavigationAction.NavigateTo(Routes.SignUp))
            }
        }
    }

    private fun loginUser() {
        viewModelScope.launch {
            state= state.copy(isLoading = true, error = null)

            authRepository.login(
                email = state.email,
                password = state.password
            ).onSuccess { user ->

                state= state.copy(isLoading = false, error = null)
                Result.success(user)
                navigate(
                    NavigationAction.NavigateTo(
                        route = Routes.Home,
                        clearBackStack = true
                    )
                )
                sessionManager.setLoggedIn()
            }.onFailure { error ->
                state = state.copy(isLoading = false, error = error.message ?: "Login failed")

            }
        }
    }
}