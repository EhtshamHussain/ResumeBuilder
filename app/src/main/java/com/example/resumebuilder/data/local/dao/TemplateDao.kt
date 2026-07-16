package com.example.resumebuilder.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.resumebuilder.data.local.entity.TemplateEntity

@Dao
interface TemplateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(templates: List<TemplateEntity>)
    @Query("SELECT * FROM templates")
    suspend fun getAllTemplates(): List<TemplateEntity>
}