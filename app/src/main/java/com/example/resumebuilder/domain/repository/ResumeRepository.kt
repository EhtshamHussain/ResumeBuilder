package com.example.resumebuilder.domain.repository

import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.model.SavedResume
import kotlinx.coroutines.flow.Flow
interface ResumeRepository {
    suspend fun saveResume(draft: ResumeDraft): Result<Long>
    suspend fun updateResume(resumeId: Long, draft: ResumeDraft): Result<Unit>
    fun getAllResumes(): Flow<List<SavedResume>>
    suspend fun getResumeById(id: Long): Result<SavedResume>
    suspend fun deleteResume(resumeId: Long): Result<Unit>
    suspend fun renderResumeHtml(resumeId: Long): Result<String>
}