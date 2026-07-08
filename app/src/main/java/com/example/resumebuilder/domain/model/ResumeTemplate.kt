package com.example.resumebuilder.domain.model

enum class ResumeTemplate(
    val id: String,
    val displayName: String,
    val description: String,
    val htmlAssetFileName: String
) {
    MODERN(
        id = "modern",
        displayName = "Modern",
        description = "Best for tech and creative roles",
        htmlAssetFileName = "template_modern.html"
    ),
    PROFESSIONAL(
        id = "professional",
        displayName = "Professional",
        description = "Ideal for executive and corporate positions",
        htmlAssetFileName = "template_professional.html"
    ),
    MINIMAL(
        id = "minimal",
        displayName = "Minimal",
        description = "Clean, distraction-free layout",
        htmlAssetFileName = "template_minimal.html"
    ),
    ATS_FRIENDLY(
        id = "ats_friendly",
        displayName = "ATS-Friendly",
        description = "Optimized for automated screening",
        htmlAssetFileName = "template_ats_friendly.html"
    );

    companion object {
        // id se enum dhoondne ke liye helper — Preview screen mein kaam ayega
        fun fromId(id: String): ResumeTemplate {
            return entries.find { it.id == id } ?: MODERN
        }
    }
}
