package com.example.resumebuilder.data.mustache

import android.content.Context
import com.example.resumebuilder.domain.model.*
import com.github.mustachejava.DefaultMustacheFactory
import java.io.StringWriter

// Ye class assets/templates/ se HTML file padhti hai, ResumeDraft ka data use karke
// Mustache placeholders ({{fullName}}, {{#workExperiences}}) fill karti hai,
// aur final complete HTML String return karti hai jo WebView load karega
class MustacheRenderer(
    private val context: Context
) {

    fun render(draft: ResumeDraft): String {
        val template = ResumeTemplate.fromId(draft.selectedTemplateId)

        // ResumeDraft (jo Lists<WorkExperience> waghera rakhta hai) ko
        // ResumeTemplateData mein convert karo — jisme Mustache-friendly wrapper objects (SkillMustache) hain,
        // aur conditional flags (hasGithub, hasProjects waghera) calculate kiye jate hain
        val templateData = ResumeTemplateData(
            fullName = draft.fullName,
            professionalTitle = draft.professionalTitle,
            email = draft.email,
            phoneNumber = draft.phoneNumber,
            currentLocation = draft.currentLocation,
            professionalSummary = draft.professionalSummary,

            workExperiences = draft.workExperiences.map {
                WorkExperienceMustache(
                    company = it.company,
                    role = it.role,
                    startDate = it.startDate,
                    endDate = it.endDate,
                    responsibilities = it.responsibilities
                )
            },
            educations = draft.educations.map {
                EducationMustache(
                    institution = it.institution,
                    degree = it.degree,
                    startDate = it.startDate,
                    graduationDate = it.graduationDate
                )
            },
            skills = draft.skills.map { SkillMustache(it) },
            projects = draft.projects,
            certifications = draft.certifications,
            languages = draft.languages.map { SkillMustache(it) },
            interests = draft.interests.map { SkillMustache(it) },
            achievements = draft.achievements.map { SkillMustache(it) },

            // Conditional flags — agar list khali hai ya URL khali hai, to us section ko HTML mein hide karna hai
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

        // assets/templates/template_xxx.html file ko open karo aur Mustache se compile karo
        val mustacheFactory = DefaultMustacheFactory()
        val mustache = context.assets
            .open("templates/${template.htmlAssetFileName}")
            .bufferedReader()
            .use { reader ->
                mustacheFactory.compile(reader, template.htmlAssetFileName)
            }

        // StringWriter mein final rendered HTML likha jayega
        val writer = StringWriter()
        mustache.execute(writer, templateData).flush()

        return writer.toString()
    }
}