package com.example.resumebuilder.presentation.resumebuilder.contactsummary

// User is screen pa jo bhi actions kar sakta hai — har TextField ka apna event
sealed class ContactSummaryEvent {
    data class FullNameChanged(val value: String) : ContactSummaryEvent()
    data class ProfessionalTitleChanged(val value: String) : ContactSummaryEvent()
    data class EmailChanged(val value: String) : ContactSummaryEvent()
    data class PhoneNumberChanged(val value: String) : ContactSummaryEvent()
    data class CurrentLocationChanged(val value: String) : ContactSummaryEvent()
    data class ProfessionalSummaryChanged(val value: String) : ContactSummaryEvent()
    data object NextStepClicked : ContactSummaryEvent()
    data object ScreenEntered : ContactSummaryEvent()   // pehle se saved draft data load karne ke liye
}