package com.example.resumebuilder.data.local

import androidx.room.TypeConverter
import com.example.resumebuilder.domain.model.Certification
import com.example.resumebuilder.domain.model.Education
import com.example.resumebuilder.domain.model.Project
import com.example.resumebuilder.domain.model.WorkExperience
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    private val gson = Gson()

    // ---- WorkExperience List ----
    @TypeConverter
    fun fromWorkExperienceList(list: List<WorkExperience>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toWorkExperienceList(json: String): List<WorkExperience> {
        val type = object : TypeToken<List<WorkExperience>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    // ---- Education List ----
    @TypeConverter
    fun fromEducationList(list: List<Education>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toEducationList(json: String): List<Education> {
        val type = object : TypeToken<List<Education>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    // ---- Project List ----
    @TypeConverter
    fun fromProjectList(list: List<Project>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toProjectList(json: String): List<Project> {
        val type = object : TypeToken<List<Project>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    // ---- Certification List ----
    @TypeConverter
    fun fromCertificationList(list: List<Certification>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toCertificationList(json: String): List<Certification> {
        val type = object : TypeToken<List<Certification>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(json: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }
}