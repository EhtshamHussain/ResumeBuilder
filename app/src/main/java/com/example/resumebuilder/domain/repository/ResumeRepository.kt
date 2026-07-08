package com.example.resumebuilder.domain.repository

import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.model.SavedResume
import kotlinx.coroutines.flow.Flow

// Ye interface Clean Architecture ka Domain layer hai — ViewModels sirf isi par depend karte hain,
// concrete Room implementation par nahi (Dependency Inversion Principle)
interface ResumeRepository {

    // Poora ResumeDraft (jo 5 screens ne collect kiya) permanently Room mein save karta hai
    // Return: naye save hue resume ka database id (taake Preview screen usko fetch kar sake)
    suspend fun saveResume(draft: ResumeDraft): Result<Long>

    suspend fun updateResume(resumeId: Long, draft: ResumeDraft): Result<Unit>


    // Home screen ki list ke liye — jab bhi naya resume add ho, ye Flow automatically update hoga
    fun getAllResumes(): Flow<List<SavedResume>>

    // Preview screen ke liye — ek specific resume ka poora data fetch karna
    suspend fun getResumeById(id: Long): Result<SavedResume>

    // Preview screen se resume delete karne ke liye (future use)
    suspend fun deleteResume(resumeId: Long): Result<Unit>

    // Ye function resume ka data lekar, selected template ke HTML mein Mustache se fill karke,
    // final rendered HTML String return karta hai — WebView isko load karega
    suspend fun renderResumeHtml(resumeId: Long): Result<String>
}