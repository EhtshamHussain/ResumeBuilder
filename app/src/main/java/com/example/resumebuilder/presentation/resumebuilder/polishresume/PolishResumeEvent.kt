package com.example.resumebuilder.presentation.resumebuilder.polishresume

sealed class PolishResumeEvent {
    data class LanguageInputChanged(val value: String) : PolishResumeEvent()
    data object AddLanguageClicked : PolishResumeEvent()
    data class RemoveLanguage(val language: String) : PolishResumeEvent()
    // Interests
    data class InterestInputChanged(val value: String) : PolishResumeEvent()
    data object AddInterestClicked : PolishResumeEvent()
    data class RemoveInterest(val interest: String) : PolishResumeEvent()
    // Achievements
    data class AchievementInputChanged(val value: String) : PolishResumeEvent()
    data object AddAchievementClicked : PolishResumeEvent()
    data class RemoveAchievement(val achievement: String) : PolishResumeEvent()
    // Professional Links
    data class LinkedInChanged(val value: String) : PolishResumeEvent()
    data class GithubChanged(val value: String) : PolishResumeEvent()
    data class PortfolioChanged(val value: String) : PolishResumeEvent()
    data class IncludeReferencesToggled(val value: Boolean) : PolishResumeEvent()
    data object SaveDraftClicked : PolishResumeEvent()
    data object FinishClicked : PolishResumeEvent()
}