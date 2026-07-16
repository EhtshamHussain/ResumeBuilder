package com.example.resumebuilder.presentation.bottombar.profilescreen

sealed class ProfileEvent {
    data object ScreenEntered : ProfileEvent()
    data object EditProfileClicked : ProfileEvent()
    data object LogoutClicked : ProfileEvent()
    data object PersonalInformationClicked : ProfileEvent()
    data object NotificationPreferencesClicked : ProfileEvent()
    data object PrivacySecurityClicked : ProfileEvent()
}