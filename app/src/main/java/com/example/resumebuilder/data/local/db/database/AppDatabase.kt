package com.example.resumebuilder.data.local.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.resumebuilder.data.local.db.dao.ResumeDao
import com.example.resumebuilder.data.local.db.dao.UserDao
import com.example.resumebuilder.data.local.db.entity.ResumeEntity
import com.example.resumebuilder.data.local.db.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        ResumeEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun resumeDao(): ResumeDao
}