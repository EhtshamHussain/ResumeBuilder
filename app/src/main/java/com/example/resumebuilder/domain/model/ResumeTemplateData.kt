// domain/model/ResumeTemplateData.kt
package com.example.resumebuilder.domain.model

// Ye class Mustache ko diya jayega — isi ke fields {{fullName}}, {{#workExperiences}} waghera
// se HTML template mein match honge. Field names EXACTLY HTML file ke placeholders se match hone chahiye.
data class ResumeTemplateData(
    val fullName: String,
    val professionalTitle: String,
    val email: String,
    val phoneNumber: String,
    val currentLocation: String,
    val professionalSummary: String,


    val workExperiences: List<WorkExperience>,
    val educations: List<Education>,
    val projects: List<Project>,
    val certifications: List<Certification>,


    val skills: List<SkillMustache>,
    val languages: List<SkillMustache>,
    val interests: List<SkillMustache>,
    val achievements: List<SkillMustache>,

    // Conditional sections ke liye Boolean flags — {{#hasGithub}}...{{/hasGithub}}
    val hasGithub: Boolean,
    val hasLinkedIn: Boolean,
    val hasPortfolio: Boolean,
    val hasProjects: Boolean,
    val hasCertifications: Boolean,
    val hasAchievements: Boolean,
    val hasLanguages: Boolean,
    val hasInterests: Boolean,

    val linkedInUrl: String,
    val githubUrl: String,
    val portfolioUrl: String,
    val includeReferences: Boolean
)

// Mustache ke andar {{#workExperiences}} loop mein, har item ke andar {{company}}, {{role}} kaam karega
data class WorkExperienceMustache(
    val company: String,
    val role: String,
    val startDate: String,
    val endDate: String,
    val responsibilities: String
)

data class EducationMustache(
    val institution: String,
    val degree: String,
    val startDate: String,
    val graduationDate: String
)

// Skills/Languages/Interests jaise simple String lists ko Mustache mein loop karne ke liye
// wrapper object chahiye hota hai kyunki Mustache "{{.}}" se raw string bhi kaam karta hai,
// lekin object approach zyada readable/safe hai
data class SkillMustache(val value: String)

//app/src/main/assets/templates/template_ats_friendly.html