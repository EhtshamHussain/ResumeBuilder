package com.example.resumebuilder.presentation.resumebuilder.skillsprojects

import com.example.resumebuilder.domain.model.Certification
import com.example.resumebuilder.domain.model.Project

data class SkillsProjectsState(
    val skills: List<String> = emptyList(),
    val currentSkillInput: String = "",
    val projects: List<Project> = emptyList(),
    val certifications: List<Certification> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)