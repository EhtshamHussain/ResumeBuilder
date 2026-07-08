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

//    @Serializable
//    data object Home : Routes()

//    @Serializable
//    data object MainScreen : Routes()

    @Serializable
    data object  OnboardingWelcomeRoute: Routes()
    @Serializable
    data object  OnboardingResumeRoute : Routes()
    @Serializable
    data object OnboardingCareerRoute:Routes()

    @Serializable
    data object Settings : Routes()

    @Serializable
    data object CreateResume : Routes()


    @Serializable
    data object EditProfile : Routes()

//    @Serializable
//    data object ResumePreview : Routes()


    //Contact Summery first Screen
    @Serializable
    data object ContactSummary : Routes()

    @Serializable
    data object  ExperienceEducation : Routes()
    @Serializable
    data object  SkillsProjects : Routes()
    @Serializable
    data object PolishResume : Routes()
    @Serializable
    data class TemplateSelect(val existingResumeId: Long? = null) : Routes()





    @Serializable
    data class ResumePreview(val resumeId: Long) : Routes()

}