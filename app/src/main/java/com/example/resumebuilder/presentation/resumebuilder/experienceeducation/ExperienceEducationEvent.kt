package com.example.resumebuilder.presentation.resumebuilder.experienceeducation

// Har event mein "id" hota hai — taake ViewModel ko pata chale
// list mein SE ITEM update/remove karna hai (kyunki multiple entries ho sakti hain)
sealed class ExperienceEducationEvent {
    data object ScreenEntered : ExperienceEducationEvent()

    // Work Experience events
    data object AddWorkExperienceClicked : ExperienceEducationEvent()
    data class RemoveWorkExperience(val id: String) : ExperienceEducationEvent()
    data class CompanyChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class RoleChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class WorkStartDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class WorkEndDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class ResponsibilitiesChanged(val id: String, val value: String) : ExperienceEducationEvent()

    // Education events
    data object AddEducationClicked : ExperienceEducationEvent()
    data class RemoveEducation(val id: String) : ExperienceEducationEvent()
    data class InstitutionChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class DegreeChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class EduStartDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class GraduationDateChanged(val id: String, val value: String) : ExperienceEducationEvent()

    data object SaveDraftClicked : ExperienceEducationEvent()
    data object NextStepClicked : ExperienceEducationEvent()
}