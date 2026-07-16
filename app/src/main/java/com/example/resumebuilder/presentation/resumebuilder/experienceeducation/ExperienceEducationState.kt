package com.example.resumebuilder.presentation.resumebuilder.experienceeducation

import com.example.resumebuilder.domain.model.Education
import com.example.resumebuilder.domain.model.WorkExperience
data class ExperienceEducationState(
    val workExperiences: List<WorkExperience> = emptyList(),
    val educations: List<Education> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)