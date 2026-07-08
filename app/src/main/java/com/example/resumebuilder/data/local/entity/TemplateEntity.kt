package com.example.resumebuilder.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

// Ye 5 templates (Modern, Professional, Minimal, ATS-Friendly, waghera) ki metadata store karta hai
// Actual HTML/CSS design assets/ folder mein rahega — ye entity sirf reference/preview info rakhta hai
@Entity(tableName = "templates")
data class TemplateEntity(
    @PrimaryKey
    val templateId: String,        // e.g. "modern", "professional", "minimal", "ats_friendly"

    val templateName: String,      // e.g. "Modern"

    val description: String,       // e.g. "Best for tech and creative roles"

    // assets/templates/ folder mein HTML file ka naam — e.g. "modern_template.html"
    val htmlAssetFileName: String,

    val isDefault: Boolean = false
)