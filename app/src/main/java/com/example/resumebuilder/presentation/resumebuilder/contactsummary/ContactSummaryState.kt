package com.example.resumebuilder.presentation.resumebuilder.contactsummary

// Is screen ka poora UI state — TextFields ki current values + loading/error
data class ContactSummaryState(

    val fullName: String = "",
    val professionalTitle: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val currentLocation: String = "",
    val professionalSummary: String = "",

    //common
    val isLoading: Boolean = false,
    val error: String? = null
)