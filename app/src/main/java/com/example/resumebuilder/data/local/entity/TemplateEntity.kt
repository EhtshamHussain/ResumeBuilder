package com.example.resumebuilder.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "templates")
data class TemplateEntity(
    @PrimaryKey
    val templateId: String,
    val templateName: String,
    val description: String,
    val htmlAssetFileName: String,
    val isDefault: Boolean = false
)