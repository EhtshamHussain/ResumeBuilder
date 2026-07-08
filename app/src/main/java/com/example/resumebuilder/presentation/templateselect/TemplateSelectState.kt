package com.example.resumebuilder.presentation.templateselect

import com.example.resumebuilder.domain.model.ResumeTemplate

data class TemplateSelectState(
    val templates: List<ResumeTemplate> = ResumeTemplate.entries,
    val selectedTemplate: ResumeTemplate? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)