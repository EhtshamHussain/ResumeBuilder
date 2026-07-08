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

    // Home screen ki list ke liye — Flow use kiya taake naya resume insert hote hi
    // UI automatically update ho jaye (bina manual refresh ke)
    @Query("SELECT * FROM resumes ORDER BY createdAt DESC")
    fun getAllResumes(): Flow<List<ResumeEntity>>

    @Query("SELECT * FROM resumes WHERE id = :id")
    suspend fun getResumeById(id: Long): ResumeEntity?


    // 👇 NAYA — Room ka @Update annotation, primary key (id) match karke row replace karta hai
    @Update
    suspend fun update(resume: ResumeEntity)

    @Delete
    suspend fun delete(resume: ResumeEntity)

    // Jab PDF generate ho jaye, uska file path update karna hai
    @Query("UPDATE resumes SET pdfFilePath = :path WHERE id = :id")
    suspend fun updatePdfPath(id: Long, path: String)
}