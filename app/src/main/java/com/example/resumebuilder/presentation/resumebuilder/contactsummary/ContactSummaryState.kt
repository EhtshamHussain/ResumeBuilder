package com.example.resumebuilder.presentation.resumebuilder.contactsummary

data class ContactSummaryState(
    val fullName: String = "",
    val professionalTitle: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val currentLocation: String = "",
    val professionalSummary: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)