package com.example.resumebuilder.presentation.resumebuilder.polishresume

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class PolishResumeViewModel(
    private val resumeDraftRepository: ResumeDraftRepository,
    private val resumeRepository: ResumeRepository
) : BaseViewModel() {
    var state by mutableStateOf(PolishResumeState())
        private set

    init {
        loadExistingDraft()
    }

    fun onEvent(event: PolishResumeEvent) {
        when (event) {
            is PolishResumeEvent.LanguageInputChanged -> {
                state = state.copy(currentLanguageInput = event.value)
            }

            is PolishResumeEvent.AddLanguageClicked -> addLanguage()
            is PolishResumeEvent.RemoveLanguage -> {
                state = state.copy(languages = state.languages.filter { it != event.language })
            }

            is PolishResumeEvent.InterestInputChanged -> {
                state = state.copy(currentInterestInput = event.value)
            }

            is PolishResumeEvent.AddInterestClicked -> addInterest()
            is PolishResumeEvent.RemoveInterest -> {
                state = state.copy(interests = state.interests.filter { it != event.interest })
            }

            is PolishResumeEvent.AchievementInputChanged -> {
                state = state.copy(currentAchievementInput = event.value)
            }

            is PolishResumeEvent.AddAchievementClicked -> addAchievement()
            is PolishResumeEvent.RemoveAchievement -> {
                state =
                    state.copy(achievements = state.achievements.filter { it != event.achievement })
            }

            is PolishResumeEvent.LinkedInChanged -> {
                state = state.copy(linkedInUrl = event.value)
            }

            is PolishResumeEvent.GithubChanged -> {
                state = state.copy(githubUrl = event.value)
            }

            is PolishResumeEvent.PortfolioChanged -> {
                state = state.copy(portfolioUrl = event.value)
            }

            is PolishResumeEvent.IncludeReferencesToggled -> {
                state = state.copy(includeReferences = event.value)
            }

            is PolishResumeEvent.SaveDraftClicked -> {
                saveToRepository()
                showToast("Draft saved")
            }

            is PolishResumeEvent.FinishClicked -> {
                finishAndProceed()
            }
        }
    }

    private fun addLanguage() {
        val language = state.currentLanguageInput.trim()
        if (language.isBlank()) return
        if (state.languages.contains(language)) {
            showError("This language is already added")
            return
        }
        state = state.copy(
            languages = state.languages + language,
            currentLanguageInput = ""
        )
    }

    private fun addInterest() {
        val interest = state.currentInterestInput.trim()
        if (interest.isBlank()) return
        if (state.interests.contains(interest)) {
            showError("This interest is already added")
            return
        }
        state = state.copy(
            interests = state.interests + interest,
            currentInterestInput = ""
        )
    }

    private fun addAchievement() {
        val achievement = state.currentAchievementInput.trim()
        if (achievement.isBlank()) return
        state = state.copy(
            achievements = state.achievements + achievement,
            currentAchievementInput = ""
        )
    }

    private fun loadExistingDraft() {
        val draft = resumeDraftRepository.draft.value
        state = state.copy(
            languages = draft.languages,
            interests = draft.interests,
            achievements = draft.achievements,
            linkedInUrl = draft.linkedInUrl,
            githubUrl = draft.githubUrl,
            portfolioUrl = draft.portfolioUrl,
            includeReferences = draft.includeReferences
        )
    }

    private fun saveToRepository() {
        resumeDraftRepository.updateDraft { currentDraft ->
            currentDraft.copy(
                languages = state.languages,
                interests = state.interests,
                achievements = state.achievements,
                linkedInUrl = state.linkedInUrl,
                githubUrl = state.githubUrl,
                portfolioUrl = state.portfolioUrl,
                includeReferences = state.includeReferences
            )
        }
    }

    private fun finishAndProceed() {
        vmScopeMain {
            saveToRepository()
            if(resumeDraftRepository.draft.value.selectedTemplateId.isNotEmpty()) {
                val finalDraft = resumeDraftRepository.draft.value
                val result = resumeRepository.saveResume(finalDraft)
                val resumeId = result.getOrNull()
                resumeId?.let {
                    navigate(
                        NavigationAction.NavigateToClearingUpTo(
                            route = Routes.ResumePreview(resumeId = it),
                            upToRoute = Routes.CreateResume,
                            inclusive = false
                        )
                    )
                }
            }else{
            navigate(
                NavigationAction.NavigateTo(
                    route = Routes.TemplateSelect(existingResumeId = null)
                )
            )
        }
            }
    }
}