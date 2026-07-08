package com.example.resumebuilder.domain.model

// Ye domain model hai jo UI/ViewModel ko dikhta hai — Room ke ResumeEntity se alag rakha hai
// taake presentation layer ko pata na ho ke data JSON string mein store hota hai (Clean Architecture)
data class SavedResume(
    val id: Long,
    val resumeName: String,
    val draft: ResumeDraft,       // poora original data wapas List<> form mein (JSON se parse karke)
    val pdfFilePath: String?,
    val createdAt: Long
)