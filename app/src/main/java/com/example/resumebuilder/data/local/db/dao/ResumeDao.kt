package com.example.resumebuilder.data.local.db.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.resumebuilder.data.local.db.entity.ResumeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ResumeDao {
    @Insert
    suspend fun insert(resume: ResumeEntity): Long
    @Query("SELECT * FROM resumes ORDER BY createdAt DESC")
    fun getAllResumes(): Flow<List<ResumeEntity>>
    @Query("SELECT * FROM resumes WHERE id = :id")
    suspend fun getResumeById(id: Long): ResumeEntity?

    @Query("SELECT * FROM resumes WHERE email = :email")
    suspend fun getResumeByEmail(email: String): ResumeEntity?
    @Update
    suspend fun update(resume: ResumeEntity)
    @Delete
    suspend fun delete(resume: ResumeEntity)
}