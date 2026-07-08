package com.example.resumebuilder.presentation.resumebuilder.skillsprojects

sealed class SkillsProjectsEvent {
    data object ScreenEntered : SkillsProjectsEvent()

    // Skills (chips) events
    data class SkillInputChanged(val value: String) : SkillsProjectsEvent()
    data object AddSkillClicked : SkillsProjectsEvent()          // "+" button ya Enter dabane pa
    data class RemoveSkill(val skill: String) : SkillsProjectsEvent()  // chip ke "x" pa click

    // Projects events
    data object AddProjectClicked : SkillsProjectsEvent()
    data class RemoveProject(val id: String) : SkillsProjectsEvent()
    data class ProjectTitleChanged(val id: String, val value: String) : SkillsProjectsEvent()
    data class ProjectLinkChanged(val id: String, val value: String) : SkillsProjectsEvent()
    data class ProjectDescriptionChanged(val id: String, val value: String) : SkillsProjectsEvent()

    // Certifications events
    data object AddCertificationClicked : SkillsProjectsEvent()
    data class RemoveCertification(val id: String) : SkillsProjectsEvent()
    data class CertificationNameChanged(val id: String, val value: String) : SkillsProjectsEvent()
    data class CertificationIssuerChanged(val id: String, val value: String) : SkillsProjectsEvent()
    data class CertificationDateChanged(val id: String, val value: String) : SkillsProjectsEvent()

    data object NextStepClicked : SkillsProjectsEvent()
}