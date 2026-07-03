package com.example.resumebuilder.presentation.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class Routes{

    @Serializable
    data object Splash : Routes()

    @Serializable
    data object Login : Routes()

    @Serializable
    data object SignUp : Routes()

    @Serializable
    data object Home : Routes()

    @Serializable
    data object  OnboardingWelcomeRoute: Routes()
    @Serializable
    data object  OnboardingResumeRoute : Routes()
    @Serializable
    data object OnboardingCareerRoute:Routes()


}