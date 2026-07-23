package com.example.resumebuilder.presentation.templateselect

import com.example.resumebuilder.domain.model.ResumeTemplate

sealed class TemplateSelectEvent {
    data class TemplateSelected(val template: ResumeTemplate) : TemplateSelectEvent()
    data class TemplateSelectedOnBb(val template : ResumeTemplate)  : TemplateSelectEvent()
}