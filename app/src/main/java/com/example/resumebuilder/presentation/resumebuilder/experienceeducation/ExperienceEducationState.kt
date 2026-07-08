package com.example.resumebuilder.presentation.resumebuilder.experienceeducation

import com.example.resumebuilder.domain.model.Education
import com.example.resumebuilder.domain.model.WorkExperience

// Is screen pa 2 dynamic lists hain: workExperiences aur educations
// User "Add Work Experience" / "Add Education" se naye empty entries add karta hai
data class ExperienceEducationState(
    val workExperiences: List<WorkExperience> = emptyList(),
    val educations: List<Education> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)