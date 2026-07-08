package com.example.resumebuilder.di

import androidx.room.Room
import com.example.resumebuilder.data.local.database.AppDatabase
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.data.mustache.MustacheRenderer
import com.example.resumebuilder.data.repository.AuthRepositoryImpl
import com.example.resumebuilder.data.repository.ResumeDraftRepositoryImpl
import com.example.resumebuilder.data.repository.ResumeRepositoryImpl
import com.example.resumebuilder.domain.repository.AuthRepository
import com.example.resumebuilder.domain.repository.ResumeDraftRepository
import com.example.resumebuilder.domain.repository.ResumeRepository
import com.example.resumebuilder.presentation.auth.login.LoginViewModel
import com.example.resumebuilder.presentation.auth.signup.SignUpViewModel
import com.example.resumebuilder.presentation.bottombar.createscreen.CreateViewModel
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeViewModel
import com.example.resumebuilder.presentation.bottombar.profilescreen.ProfileViewModel
import com.example.resumebuilder.presentation.resumebuilder.contactsummary.ContactSummaryViewModel
import com.example.resumebuilder.presentation.resumebuilder.experienceeducation.ExperienceEducationViewModel
import com.example.resumebuilder.presentation.resumebuilder.polishresume.PolishResumeViewModel
import com.example.resumebuilder.presentation.resumebuilder.resumepreview.ResumePreviewViewModel
import com.example.resumebuilder.presentation.resumebuilder.skillsprojects.SkillsProjectsViewModel
import com.example.resumebuilder.presentation.templateselect.TemplateSelectViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val databaseModule = module {

    single {

        Room.databaseBuilder(
                get(),
                AppDatabase::class.java,
                "resume_db"
            ).fallbackToDestructiveMigration(true).build()
    }

    single { get<AppDatabase>().userDao() }

    single { get<AppDatabase>().resumeDao() }

    single { get<AppDatabase>().templateDao() }

    single { SessionManager(get()) }


}
val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<ResumeDraftRepository>{ ResumeDraftRepositoryImpl() }

//    single { MustacheRenderer(context = androidContext()) }
       singleOf(::MustacheRenderer)

    single<ResumeRepository> {
        ResumeRepositoryImpl(
            resumeDao = get<AppDatabase>().resumeDao(),
            mustacheRenderer = get()
        )
    }

//    single<ResumeRepository>(ResumeRepositoryImpl(get()))

}

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignUpViewModel)
    viewModelOf ( ::HomeViewModel )
    viewModelOf( ::CreateViewModel )
    viewModelOf( ::ProfileViewModel)
    viewModelOf (:: ContactSummaryViewModel)

    viewModelOf (:: ContactSummaryViewModel)
    viewModelOf (::ExperienceEducationViewModel )
    viewModelOf (::SkillsProjectsViewModel )
    viewModelOf (::PolishResumeViewModel )

    viewModel { (existingResumeId: Long?) ->
        TemplateSelectViewModel(
            existingResumeId = existingResumeId,
            resumeDraftRepository = get(),
            resumeRepository = get()
        )
    }

    viewModel { (resumeId: Long) ->
        ResumePreviewViewModel(
            resumeId = resumeId,
            resumeRepository = get(),
            resumeDraftRepository = get()   // 👈 naya param add hua
        )
    }


    viewModel { ProfileViewModel(sessionManager = get(), resumeRepository = get()) }
}

