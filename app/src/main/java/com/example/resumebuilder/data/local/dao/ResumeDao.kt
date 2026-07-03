package com.example.resumebuilder.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.resumebuilder.data.local.entity.ResumeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResumeDao {

    @Insert
    suspend fun insertResume(resume: ResumeEntity)

    @Update
    suspend fun updateResume(resume: ResumeEntity)

    @Delete
    suspend fun deleteResume(resume: ResumeEntity)

    @Query("SELECT * FROM resumes WHERE userId=:userId")
    fun getResumes(userId: Long): Flow<List<ResumeEntity>>

    @Query("SELECT * FROM resumes WHERE id=:id")
    suspend fun getResume(id: Long): ResumeEntity?
}