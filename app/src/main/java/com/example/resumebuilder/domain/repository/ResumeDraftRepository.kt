// domain/repository/ResumeDraftRepository.kt
package com.example.resumebuilder.domain.repository

import com.example.resumebuilder.domain.model.ResumeDraft
import kotlinx.coroutines.flow.StateFlow

// Ye interface hai — taake ViewModels is par depend karein, concrete implementation pa nahi
// (Clean Architecture ka Dependency Inversion principle)
interface ResumeDraftRepository {

    // Current draft ki state — sab screens isko observe/read kar sakti hain
    val draft: StateFlow<ResumeDraft>

    // Poore draft ko ek naye draft se replace karna (partial update ke liye use hoga)
    fun updateDraft(update: (ResumeDraft) -> ResumeDraft)

    // Jab user resume completely bana le aur naya banana chahe, purana draft clear karna
    fun clearDraft()
}