package com.example.resumebuilder.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

// Poora resume data ek hi row mein store hoga.
// Lists (workExperiences, skills, waghera) ko JSON String mein convert karke store karenge,
// kyunki Room directly List<CustomObject> store nahi kar sakta bina TypeConverter ke.
@Entity(tableName = "resumes")
data class ResumeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    // File/display name jo Home screen pa dikhega — jese "Ehtsham_Hussain_Resume"
    val resumeName: String,

    // Contact & Summary
    val fullName: String,
    val professionalTitle: String,
    val email: String,
    val phoneNumber: String,
    val currentLocation: String,
    val professionalSummary: String,

    // Ye saari lists JSON string ki tarah save hongi (Gson se convert karke)
    val workExperiencesJson: String,
    val educationsJson: String,
    val skillsJson: String,
    val projectsJson: String,
    val certificationsJson: String,
    val languagesJson: String,
    val interestsJson: String,
    val achievementsJson: String,

    // Professional Links
    val linkedInUrl: String,
    val githubUrl: String,
    val portfolioUrl: String,
    val includeReferences: Boolean,

    // Konsa template select hua tha
    val selectedTemplateId: String,

    // Generated PDF ka file path (jab download hoga tab set hoga)
    val pdfFilePath: String? = null,

    // Kab bana — Home screen pa sort/display ke liye
    val createdAt: Long = System.currentTimeMillis()
)
