package com.example.resumebuilder.presentation.resumebuilder.contactsummary

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

// Ye screen apna khud ka state rakhta hai (jese Login/SignUp mein tha),
// lekin "Next Step" click hone pa apna data shared ResumeDraftRepository mein save karta hai
class ContactSummaryViewModel(
    private val resumeDraftRepository: ResumeDraftRepository
) : BaseViewModel() {

    var state by mutableStateOf(ContactSummaryState())
        private set

    fun onEvent(event: ContactSummaryEvent) {
        when (event) {
            is ContactSummaryEvent.ScreenEntered -> {
                loadExistingDraft()
            }

            is ContactSummaryEvent.FullNameChanged -> {
                state = state.copy(fullName = event.value)
            }

            is ContactSummaryEvent.ProfessionalTitleChanged -> {
                state = state.copy(professionalTitle = event.value)
            }

            is ContactSummaryEvent.EmailChanged -> {
                state = state.copy(email = event.value)
            }

            is ContactSummaryEvent.PhoneNumberChanged -> {
                state = state.copy(phoneNumber = event.value)
            }

            is ContactSummaryEvent.CurrentLocationChanged -> {
                state = state.copy(currentLocation = event.value)
            }

            is ContactSummaryEvent.ProfessionalSummaryChanged -> {
                state = state.copy(professionalSummary = event.value)
            }

            is ContactSummaryEvent.NextStepClicked -> {
                validateAndProceed()
            }
        }
    }

    // Agar user pehle se kuch data bhar chuka hai (back aake dobara is screen pa aya),
    // to shared repository se wo purana data wapas state mein le aao
    private fun loadExistingDraft() {
        val draft = resumeDraftRepository.draft.value
        state = state.copy(
            fullName = draft.fullName,
            professionalTitle = draft.professionalTitle,
            email = draft.email,
            phoneNumber = draft.phoneNumber,
            currentLocation = draft.currentLocation,
            professionalSummary = draft.professionalSummary
        )
    }

    private fun validateAndProceed() {
        // Basic validation — sirf Full Name aur Email zaroori hain
        if (state.fullName.isBlank()) {
            showError("Please enter your full name")
            return
        }
        if (state.email.isBlank()) {
            showError("Please enter your email address")
            return
        }

        vmScopeMain {
            // Is screen ka data shared ResumeDraft mein save kar do
            resumeDraftRepository.updateDraft { currentDraft ->
                currentDraft.copy(
                    fullName = state.fullName,
                    professionalTitle = state.professionalTitle,
                    email = state.email,
                    phoneNumber = state.phoneNumber,
                    currentLocation = state.currentLocation,
                    professionalSummary = state.professionalSummary
                )
            }

            // Agli screen (Experience) pa navigate karo
            navigate(NavigationAction.NavigateTo(Routes.ExperienceEducation))
        }
    }
}