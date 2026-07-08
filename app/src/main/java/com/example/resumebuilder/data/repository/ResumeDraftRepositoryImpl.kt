// data/repository/ResumeDraftRepositoryImpl.kt
package com.example.resumebuilder.data.repository

import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Ye class Koin mein "single { }" ki tarah register hogi — matlab
// poori app mein sirf EK instance banega, jo sab ViewModels share karenge.
// Isi wajah se Screen 1 mein dala data Screen 4 tak "carry" hota rahega.
class ResumeDraftRepositoryImpl : ResumeDraftRepository {

    // Private mutable state — sirf ye class isko change kar sakti hai
    private val _draft = MutableStateFlow(ResumeDraft())

    // Public read-only state — ViewModels sirf isko observe/read karenge
    override val draft: StateFlow<ResumeDraft> = _draft.asStateFlow()

    override fun updateDraft(update: (ResumeDraft) -> ResumeDraft) {
        // Purani value lo, uspar "update" lambda apply karo, naya set kar do
        _draft.value = update(_draft.value)
    }

    override fun clearDraft() {
        _draft.value = ResumeDraft()
    }
}