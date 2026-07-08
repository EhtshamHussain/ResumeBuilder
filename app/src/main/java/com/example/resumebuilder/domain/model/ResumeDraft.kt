// domain/model/ResumeDraft.kt
package com.example.resumebuilder.domain.model

// Ye poore resume ka "in-progress" data hai — jab tak user save/download nahi karta
// Har screen (Contact, Experience, Skills, Polish) isi model ke alag alag parts ko update karegi
data class ResumeDraft(
    // Screen 1: Contact & Summary
    val fullName: String = "",
    val professionalTitle: String = "",
    val email: String = "",
    val phoneNumber: String = "",
    val currentLocation: String = "",
    val professionalSummary: String = "",

    // Screen 2: Experience & Education
    val workExperiences: List<WorkExperience> = emptyList(),
    val educations: List<Education> = emptyList(),

    // Screen 3: Skills & Projects
    val skills: List<String> = emptyList(),
    val projects: List<Project> = emptyList(),
    val certifications: List<Certification> = emptyList(),

    // Screen 4: Polish (Final Details)
    val languages: List<String> = emptyList(),
    val interests: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val linkedInUrl: String = "",
    val githubUrl: String = "",
    val portfolioUrl: String = "",
    val includeReferences: Boolean = false,

    // Screen 5: Template Selection
    val selectedTemplateId: String = ""
)

// Ek work experience entry (user "Add Work Experience" se multiple add kar sakta hai)
data class WorkExperience(
    val id: String,               // unique id — list mein update/remove karne ke liye
    val company: String = "",
    val role: String = "",
    val startDate: String = "",
    val endDate: String = "",
    val responsibilities: String = ""
)

// Ek education entry
data class Education(
    val id: String,
    val institution: String = "",
    val degree: String = "",
    val startDate: String = "",
    val graduationDate: String = ""
)

// Ek project entry
data class Project(
    val id: String,
    val title: String = "",
    val link: String = "",
    val description: String = ""
)

// Ek certification entry
data class Certification(
    val id: String,
    val name: String = "",
    val issuer: String = "",
    val issuedDate: String = ""
)