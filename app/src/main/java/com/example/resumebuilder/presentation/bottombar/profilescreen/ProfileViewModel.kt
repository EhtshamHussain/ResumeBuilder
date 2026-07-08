package com.example.resumebuilder.presentation.bottombar.profilescreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sessionManager: SessionManager,
    private val resumeRepository: ResumeRepository   // 👈 naya — resume count ke liye chahiye
) : BaseViewModel() {

    var state by mutableStateOf(ProfileState())
        private set

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.ScreenEntered -> {
                loadProfile()
                observeResumeCount()
            }

            is ProfileEvent.EditProfileClicked -> {
                navigate(NavigationAction.NavigateTo(Routes.EditProfile))
            }

            is ProfileEvent.LogoutClicked -> {
                logout()
            }

            // Abhi ye 3 events sirf placeholder hain — koi navigation/logic nahi
            // (jaisa tumne bola tha "wo bs show hon working ne krni")
            is ProfileEvent.PersonalInformationClicked -> { /* future mein implement hoga */ }
            is ProfileEvent.NotificationPreferencesClicked -> { /* future mein implement hoga */ }
            is ProfileEvent.PrivacySecurityClicked -> { /* future mein implement hoga */ }
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)

            // Placeholder — jab UserRepository/session se real naam-email milega, yahan use karo
            state = state.copy(
                isLoading = false,
                name = sessionManager.getUserName() ?: "User",     // ye method SessionManager mein banana hoga agar nahi hai
                email = sessionManager.getUserEmail() ?: ""         // ye bhi
            )
        }
    }

    // Room se live resume count — jab bhi naya resume banega ye automatically update hoga
    private fun observeResumeCount() {
        resumeRepository.getAllResumes()
            .onEach { resumes ->
                state = state.copy(totalResumesCreated = resumes.size)
            }
            .launchIn(viewModelScope)
    }

    private fun logout() {
        sessionManager.logout()
        navigate(
            NavigationAction.NavigateTo(
                route = Routes.Login,
                clearBackStack = true
            )
        )
    }
}