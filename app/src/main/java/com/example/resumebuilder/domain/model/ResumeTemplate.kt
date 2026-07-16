package com.example.resumebuilder.domain.model

import com.example.resumebuilder.R

enum class ResumeTemplate(
    val id: String,
    val displayName: String,
    val description: String,
    val htmlAssetFileName: String,
    val img : Int ,
) {
    MODERN(
        id = "modern",
        displayName = "Modern",
        description = "Best for tech and creative roles",
        htmlAssetFileName = "template_modern.html",
        img = R.drawable.modernresumetemplate
    ),
    PROFESSIONAL(
        id = "professional",
        displayName = "Professional",
        description = "Ideal for executive and corporate positions",
        htmlAssetFileName = "template_professional.html",
        img = R.drawable.prfessionalresumetemplate
    ),
    MINIMAL(
        id = "minimal",
        displayName = "Minimal",
        description = "Clean, distraction-free layout",
        htmlAssetFileName = "template_minimal.html",
        img = R.drawable.minimalresumetemplate
    ),
    ATS_FRIENDLY(
        id = "ats_friendly",
        displayName = "ATS-Friendly",
        description = "Optimized for automated screening",
        htmlAssetFileName = "template_ats_friendly.html",
        img = R.drawable.atsresumetemplate
    );
    companion object {
        fun fromId(id: String): ResumeTemplate {
            return entries.find { it.id == id } ?: MODERN
        }
    }
}
