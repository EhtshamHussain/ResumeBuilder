package com.example.resumebuilder.data.repository
import android.util.Log
import com.example.resumebuilder.data.local.dao.ResumeDao
import com.example.resumebuilder.data.mapper.ResumeMapper
import com.example.resumebuilder.data.mustache.MustacheRenderer
import com.example.resumebuilder.domain.model.ResumeDraft
import com.example.resumebuilder.domain.model.SavedResume
import com.example.resumebuilder.domain.repository.ResumeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
class ResumeRepositoryImpl(
    private val resumeDao: ResumeDao,
    private val mustacheRenderer: MustacheRenderer
) : ResumeRepository {
    override suspend fun saveResume(draft: ResumeDraft): Result<Long> {
        return try {
            val resumeName = draft.fullName
                .trim()
                .replace(" ", "_")
                .plus("_Resume")
                .ifBlank { "Untitled_Resume" }
            val entity = ResumeMapper.draftToEntity(draft, resumeName)
            val insertedId = resumeDao.insert(entity)
            Result.success(insertedId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override fun getAllResumes(): Flow<List<SavedResume>> {
        return resumeDao.getAllResumes().map { entities ->
            entities.map { ResumeMapper.entityToDomain(it) }
        }
    }
    override suspend fun updateResume(resumeId: Long, draft: ResumeDraft): Result<Unit> {
        return try {
            val resumeName = draft.fullName
                .trim()
                .replace(" ", "_")
                .plus("_Resume")
                .ifBlank { "Untitled_Resume" }
            val entity = ResumeMapper.draftToEntity(draft, resumeName).copy(id = resumeId)
            resumeDao.update(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun getResumeById(id: Long): Result<SavedResume> {
        return try {
            val entity = resumeDao.getResumeById(id)
            if (entity != null) {
                Result.success(ResumeMapper.entityToDomain(entity))
            } else {
                Result.failure(Exception("Resume not found"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun deleteResume(resumeId: Long): Result<Unit> {
        return try {
            val entity = resumeDao.getResumeById(resumeId)
                ?: return Result.failure(Exception("Resume not found"))
            resumeDao.delete(entity)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    override suspend fun renderResumeHtml(resumeId: Long): Result<String> {
        return try {
            val entity = resumeDao.getResumeById(resumeId)
                ?: return Result.failure(Exception("Resume not found"))
            val savedResume = ResumeMapper.entityToDomain(entity)
            val html = mustacheRenderer.render(savedResume.draft)
            Log.d("TAG", "renderResumeHtml: ${html}")
            Result.success(html)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}