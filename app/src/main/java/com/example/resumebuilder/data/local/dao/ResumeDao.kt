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
    suspend fun insert(resume: ResumeEntity): Long   // returns generated row id

    @Query("SELECT * FROM resumes ORDER BY createdAt DESC")
    fun getAllResumes(): Flow<List<ResumeEntity>>

    @Query("SELECT * FROM resumes WHERE id = :id")
    suspend fun getResumeById(id: Long): ResumeEntity?


    @Update
    suspend fun update(resume: ResumeEntity)

    @Delete
    suspend fun delete(resume: ResumeEntity)

    @Query("UPDATE resumes SET pdfFilePath = :path WHERE id = :id")
    suspend fun updatePdfPath(id: Long, path: String)
}