package com.example.resumebuilder.domain.model

data class ResumeDraft(
    val fullName: String = "",
    val professionalTitle: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val currentLocation: String = "",
    val professionalSummary: String = "",
    val workExperiences: List<WorkExperience> = emptyList(),
    val educations: List<Education> = emptyList(),
    val skills: List<String> = emptyList(),
    val projects: List<Project> = emptyList(),
    val certifications: List<Certification> = emptyList(),
    val languages: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val linkedInUrl: String = "",
    val githubUrl: String = "",
    val portfolioUrl: String = "",
    val includeReferences: Boolean = false,
    val selectedTemplateId: String = ""
)
data class WorkExperience(
    val id: String,
    val company: String = "",
    val role: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val responsibilities: String = ""
)
data class Education(
    val id: String,
    val institution: String = "",
    val degree: String = "",
    val startDate: String = "",
    val graduationDate: String = ""
)
data class Project(
    val id: String,
    val title: String = "",
    val link: String = "",
    val description: String = ""
)
data class Certification(
    val id: String,
    val name: String = "",
    val issuer: String = "",
    val issuedDate: String = ""
)