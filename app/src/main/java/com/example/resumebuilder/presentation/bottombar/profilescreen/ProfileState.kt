package com.example.resumebuilder.presentation.bottombar.profilescreen

data class ProfileState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",
    val totalResumesCreated: Int = 0,
    val jobMatches: Int = 12,
    val matchRatePercent: Int = 84,
    val error: String? = null
)