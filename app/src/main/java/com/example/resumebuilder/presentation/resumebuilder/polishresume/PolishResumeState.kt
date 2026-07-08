package com.example.resumebuilder.presentation.resumebuilder.polishresume

// Languages aur Interests dono chips ki tarah hain (Skills jese)
// isliye dono ke apne alag "current input" fields hain
data class PolishResumeState(
    val languages: List<String> = emptyList(),
    val currentLanguageInput: String = "",

    val interests: List<String> = emptyList(),
    val currentInterestInput: String = "",

    val achievements: List<String> = emptyList(),
    val currentAchievementInput: String = "",

    val linkedInUrl: String = "",
    val githubUrl: String = "",
    val portfolioUrl: String = "",
    val includeReferences: Boolean = false,

    val isLoading: Boolean = false,
    val error: String? = null
)