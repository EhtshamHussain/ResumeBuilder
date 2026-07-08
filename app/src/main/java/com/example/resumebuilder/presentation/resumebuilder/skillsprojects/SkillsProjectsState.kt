package com.example.resumebuilder.presentation.resumebuilder.skillsprojects

import com.example.resumebuilder.domain.model.Certification
import com.example.resumebuilder.domain.model.Project

// Skills ek simple List<String> hai (chips ki tarah dikhengi)
// currentSkillInput wo temporary text hai jo user TextField mein type kar raha hai
// jab tak wo "Enter" ya "+" na dabaye, tab tak skills list mein add nahi hota
data class SkillsProjectsState(
    val skills: List<String> = emptyList(),
    val currentSkillInput: String = "",
    val projects: List<Project> = emptyList(),
    val certifications: List<Certification> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)