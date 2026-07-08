package com.example.resumebuilder.presentation.bottombar.profilescreen

sealed class ProfileEvent {
    data object ScreenEntered : ProfileEvent()
    data object EditProfileClicked : ProfileEvent()
    data object LogoutClicked : ProfileEvent()

    // Account Settings ki 3 rows — abhi sirf UI hai, click pa kuch nahi hoga (jaisa tumne bola)
    data object PersonalInformationClicked : ProfileEvent()
    data object NotificationPreferencesClicked : ProfileEvent()
    data object PrivacySecurityClicked : ProfileEvent()
}