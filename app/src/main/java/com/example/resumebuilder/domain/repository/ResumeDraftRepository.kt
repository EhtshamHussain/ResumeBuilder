package com.example.resumebuilder.domain.repository
import com.example.resumebuilder.domain.model.ResumeDraft
import kotlinx.coroutines.flow.StateFlow
interface ResumeDraftRepository {
        val draft: StateFlow<ResumeDraft>
        fun updateDraft(update: (ResumeDraft) -> ResumeDraft) //higher order fun
        fun clearDraft()
}