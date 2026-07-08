package com.example.resumebuilder.data.mapper

import com.example.resumebuilder.data.local.entity.ResumeEntity
import com.example.resumebuilder.domain.model.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Ye object sirf conversion ka kaam karta hai: ResumeDraft -> ResumeEntity (save karte waqt)
// aur ResumeEntity -> SavedResume (fetch karte waqt). Gson yahan JSON serialize/deserialize karta hai.
object ResumeMapper {

    private val gson = Gson()

    // Save karte waqt: ResumeDraft (jisme Lists hain) ko ResumeEntity (jisme JSON strings hain) mein convert karo
    fun draftToEntity(draft: ResumeDraft, resumeName: String): ResumeEntity {
        return ResumeEntity(
            resumeName = resumeName,
            fullName = draft.fullName,
            professionalTitle = draft.professionalTitle,
            email = draft.email,
            phoneNumber = draft.phoneNumber,
            currentLocation = draft.currentLocation,
            professionalSummary = draft.professionalSummary,
            workExperiencesJson = gson.toJson(draft.workExperiences),
            educationsJson = gson.toJson(draft.educations),
            skillsJson = gson.toJson(draft.skills),
            projectsJson = gson.toJson(draft.projects),
            certificationsJson = gson.toJson(draft.certifications),
            languagesJson = gson.toJson(draft.languages),
            interestsJson = gson.toJson(draft.interests),
            achievementsJson = gson.toJson(draft.achievements),
            linkedInUrl = draft.linkedInUrl,
            githubUrl = draft.githubUrl,
            portfolioUrl = draft.portfolioUrl,
            includeReferences = draft.includeReferences,
            selectedTemplateId = draft.selectedTemplateId
        )
    }

    // Fetch karte waqt: ResumeEntity (JSON strings) ko wapas SavedResume (Lists) mein convert karo
    fun entityToDomain(entity: ResumeEntity): SavedResume {
        val draft = ResumeDraft(
            fullName = entity.fullName,
            professionalTitle = entity.professionalTitle,
            email = entity.email,
            phoneNumber = entity.phoneNumber,
            currentLocation = entity.currentLocation,
            professionalSummary = entity.professionalSummary,
            workExperiences = gson.fromJson(
                entity.workExperiencesJson,
                object : TypeToken<List<WorkExperience>>() {}.type
            ),
            educations = gson.fromJson(
                entity.educationsJson,
                object : TypeToken<List<Education>>() {}.type
            ),
            skills = gson.fromJson(
                entity.skillsJson,
                object : TypeToken<List<String>>() {}.type
            ),
            projects = gson.fromJson(
                entity.projectsJson,
                object : TypeToken<List<Project>>() {}.type
            ),
            certifications = gson.fromJson(
                entity.certificationsJson,
                object : TypeToken<List<Certification>>() {}.type
            ),
            languages = gson.fromJson(
                entity.languagesJson,
                object : TypeToken<List<String>>() {}.type
            ),
            interests = gson.fromJson(
                entity.interestsJson,
                object : TypeToken<List<String>>() {}.type
            ),
            achievements = gson.fromJson(
                entity.achievementsJson,
                object : TypeToken<List<String>>() {}.type
            ),
            linkedInUrl = entity.linkedInUrl,
            githubUrl = entity.githubUrl,
            portfolioUrl = entity.portfolioUrl,
            includeReferences = entity.includeReferences,
            selectedTemplateId = entity.selectedTemplateId
        )

        return SavedResume(
            id = entity.id,
            resumeName = entity.resumeName,
            draft = draft,
            pdfFilePath = entity.pdfFilePath,
            createdAt = entity.createdAt
        )
    }
}