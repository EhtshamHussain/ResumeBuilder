    // presentation/auth/signup/SignUpViewModel.kt
    package com.example.resumebuilder.presentation.auth.signup

    import androidx.compose.runtime.getValue
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.setValue
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.example.resumebuilder.data.local.preference.SessionManager
    import com.example.resumebuilder.domain.repository.AuthRepository
    import com.example.resumebuilder.presentation.bottombar.screens.BottomBarScreens
    import com.example.resumebuilder.presentation.navigation.Routes
    import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
    import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
    import kotlinx.coroutines.flow.MutableStateFlow
    import kotlinx.coroutines.flow.StateFlow
    import kotlinx.coroutines.flow.update
    import kotlinx.coroutines.launch
    import org.koin.androidx.compose.koinViewModel

    class SignUpViewModel(
        private val authRepository: AuthRepository,
        private val sessionManager: SessionManager
    ) : BaseViewModel() {

        var state by mutableStateOf(SignUpState())
            private set
    //
    //    private val _state = MutableStateFlow(SignUpState())
    //    val state: StateFlow<SignUpState> = _state

        fun onEvent(event: SignUpEvent) {
            when (event) {
                is SignUpEvent.NameChanged -> {
                    state=state.copy(name = event.name)
                }
                is SignUpEvent.EmailChanged -> {
                    state=state.copy(email = event.email)            }
                is SignUpEvent.PasswordChanged -> {
                    state=state.copy(password = event.password)            }
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
                state= state.copy(isLoading = true )

                authRepository.signup(
                    name = state.name,
                    email = state.email,
                    password = state.password
                ).onSuccess { user ->
                    sessionManager.setLoggedIn(name = user.name , user.email)
                    Result.success(user)
                    state= state.copy( isLoading = false)
                    navigate(
                        NavigationAction.NavigateTo(
                            route = BottomBarScreens.Home,
                            clearBackStack = true
                        )
                    )

                }.onFailure { error ->
                   state= state.copy(isLoading = false, error = error.message ?: "Signup failed")
                }
            }
        }
    }