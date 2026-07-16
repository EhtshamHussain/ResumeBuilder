package com.example.resumebuilder.presentation.resumebuilder.experienceeducation

sealed class ExperienceEducationEvent {
    data object AddWorkExperienceClicked : ExperienceEducationEvent()
    data class RemoveWorkExperience(val id: String) : ExperienceEducationEvent()
    data class CompanyChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class RoleChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class WorkStartDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class WorkEndDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class ResponsibilitiesChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data object AddEducationClicked : ExperienceEducationEvent()
    data class RemoveEducation(val id: String) : ExperienceEducationEvent()
    data class InstitutionChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class DegreeChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class EduStartDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data class GraduationDateChanged(val id: String, val value: String) : ExperienceEducationEvent()
    data object SaveDraftClicked : ExperienceEducationEvent()
    data object NextStepClicked : ExperienceEducationEvent()
}