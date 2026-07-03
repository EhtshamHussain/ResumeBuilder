package com.example.resumebuilder.presentation.onboarding.onboardingevent

sealed interface OnboardingEvent{

    data object Next:OnboardingEvent

    data object Skip:OnboardingEvent

    data object Finish:OnboardingEvent

}