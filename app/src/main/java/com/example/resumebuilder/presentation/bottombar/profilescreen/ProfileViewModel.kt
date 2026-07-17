package com.example.resumebuilder.presentation.bottombar.profilescreen

import android.widget.Toast
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
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val sessionManager: SessionManager,
    private val resumeRepository: ResumeRepository
) : BaseViewModel() {
    var state by mutableStateOf(ProfileState())
        private set

     init {
         loadProfile()
         observeResumeCount()
     }

    fun onEvent(event: ProfileEvent) {
        when (event) {
//            is ProfileEvent.ScreenEntered -> {
//                loadProfile()
//                observeResumeCount()
//            }

            is ProfileEvent.EditProfileClicked -> {
                navigate(NavigationAction.NavigateTo(Routes.EditProfile))
            }

            is ProfileEvent.LogoutClicked -> {
                logout()
            }

            is ProfileEvent.PersonalInformationClicked -> {
                showToast("Personal Information Will Come Soon In Next Update")
            /* future mein implement hoga */
            }

            is ProfileEvent.NotificationPreferencesClicked -> {
                showToast("Notification Preferences Will Come Soon In Next Update")
            /* future mein implement hoga */
            }

            is ProfileEvent.PrivacySecurityClicked -> {
                showToast("Privacy & Security Will Come Soon In Next Update")

            /* future mein implement hoga */
            }
        }
    }
    private fun loadProfile() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, error = null)
            state = state.copy(
                isLoading = false,
                name = sessionManager.getUserName()
                    ?: "User",
                email = sessionManager.getUserEmail() ?: ""
            )
        }
    }
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