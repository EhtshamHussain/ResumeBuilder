package com.example.resumebuilder.di

import androidx.room.Room
import com.example.resumebuilder.data.local.database.AppDatabase
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.data.repository.AuthRepositoryImpl
import com.example.resumebuilder.domain.repository.AuthRepository
import com.example.resumebuilder.presentation.auth.login.LoginViewModel
import com.example.resumebuilder.presentation.auth.signup.SignUpViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val databaseModule = module {

    single {

        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "resume_db"
        ).build()
    }

    single { get<AppDatabase>().userDao() }

    single { get<AppDatabase>().resumeDao() }

    single { get<AppDatabase>().templateDao() }

    single { SessionManager(get()) }

}
val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }

}

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignUpViewModel)

}

