package com.example.resumebuilder.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "resumes")
data class ResumeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val resumeName: String,
    val fullName: String,
    val professionalTitle: String,
    val email: String,
    val phoneNumber: String,
    val currentLocation: String,
    val professionalSummary: String,
    val workExperiencesJson: String,
    val educationsJson: String,
    val skillsJson: String,
    val projectsJson: String,
    val certificationsJson: String,
    val languagesJson: String,
    val interestsJson: String,
    val achievementsJson: String,
    val linkedInUrl: String,
    val githubUrl: String,
    val portfolioUrl: String,
    val includeReferences: Boolean,
    val selectedTemplateId: String,
    val pdfFilePath: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
