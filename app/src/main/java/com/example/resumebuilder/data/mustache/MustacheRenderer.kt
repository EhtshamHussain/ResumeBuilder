package com.example.resumebuilder.data.mustache

import android.content.Context
import com.example.resumebuilder.domain.model.*
import com.github.mustachejava.DefaultMustacheFactory
import java.io.StringWriter

/***This class reads an HTML file from assets/templates/,
 * uses a Mustache template engine to populate placeholders
 * (like {{fullName}} and {{#workExperiences}}) with ResumeDraft data,
 * and returns the final completed HTML string that a WebView can load and display.
 */
class MustacheRenderer(
    private val context: Context
) {
    fun render(draft: ResumeDraft): String {
        val template = ResumeTemplate.fromId(draft.selectedTemplateId)
        val templateData = ResumeTemplateData(
            fullName = draft.fullName,
            professionalTitle = draft.professionalTitle,
            email = draft.email,
            phoneNumber = draft.phoneNumber,
            currentLocation = draft.currentLocation,
            professionalSummary = draft.professionalSummary,
            workExperiences = draft.workExperiences,
            educations = draft.educations,
            projects = draft.projects,
            certifications = draft.certifications,
            skills = draft.skills.map { SkillMustache(it) },
            languages = draft.languages.map { SkillMustache(it) },
            interests = draft.interests.map { SkillMustache(it) },
            achievements = draft.achievements.map { SkillMustache(it) },
            hasGithub = draft.githubUrl.isNotBlank(),
            hasLinkedIn = draft.linkedInUrl.isNotBlank(),
            hasPortfolio = draft.portfolioUrl.isNotBlank(),
            hasProjects = draft.projects.isNotEmpty(),
            hasCertifications = draft.certifications.isNotEmpty(),
            hasAchievements = draft.achievements.isNotEmpty(),
            hasLanguages = draft.languages.isNotEmpty(),
            hasInterests = draft.interests.isNotEmpty(),
            linkedInUrl = draft.linkedInUrl,
            githubUrl = draft.githubUrl,
            portfolioUrl = draft.portfolioUrl,
            includeReferences = draft.includeReferences
        )
        val mustacheFactory = DefaultMustacheFactory()
        val mustache = context.assets
            .open("templates/${template.htmlAssetFileName}") //give input stream of raw bytes
            .bufferedReader()
            .use { reader ->
                mustacheFactory.compile(reader, template.htmlAssetFileName)
            }
        val writer = StringWriter()
        mustache.execute(writer, templateData).flush()
        return writer.toString()
    }
}

