package com.example.resumebuilder.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "resumes",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("userId")]
)
data class ResumeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    val userId: Long ,

    val title: String,

    val templateId: Int,

    val fullName: String,

    val email: String,

    val phone: String,

    val location: String,

    val summary: String,

    val skills: String,

    val experience: String,

    val education: String,

    val projects: String,

    val createdAt: Long
)