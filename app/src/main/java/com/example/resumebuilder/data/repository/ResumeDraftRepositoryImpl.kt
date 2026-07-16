package com.example.resumebuilder.data.repository
import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
class ResumeDraftRepositoryImpl : ResumeDraftRepository {
    private val _draft = MutableStateFlow(ResumeDraft())

    override val draft: StateFlow<ResumeDraft> = _draft.asStateFlow()

    override fun updateDraft(update: (ResumeDraft) -> ResumeDraft) {
        _draft.value = update(_draft.value)
    }
    override fun clearDraft() {
        _draft.value = ResumeDraft()
    }
}