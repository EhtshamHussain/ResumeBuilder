package com.example.resumebuilder.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resumebuilder.data.local.dao.ResumeDao
import com.example.resumebuilder.data.local.dao.TemplateDao
import com.example.resumebuilder.data.local.dao.UserDao
import com.example.resumebuilder.data.local.entity.ResumeEntity
import com.example.resumebuilder.data.local.entity.TemplateEntity
import com.example.resumebuilder.data.local.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ResumeEntity::class,
        TemplateEntity::class
    ],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun resumeDao(): ResumeDao

    abstract fun templateDao(): TemplateDao
}