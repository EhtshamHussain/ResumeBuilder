package com.example.resumebuilder.presentation.bottombar.profilescreen

data class ProfileState(
    val isLoading: Boolean = false,
    val name: String = "",
    val email: String = "",

    // Activity Overview card ke liye — actual resume count Room se aayega
    val totalResumesCreated: Int = 0,

    // Ye 2 abhi static/placeholder hain kyunki inka koi real data source nahi hai
    val jobMatches: Int = 12,
    val matchRatePercent: Int = 84,

    val error: String? = null
)