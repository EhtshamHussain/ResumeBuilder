package com.example.resumebuilder.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.presentation.auth.login.LoginScreen
import com.example.resumebuilder.presentation.auth.login.LoginViewModel
import com.example.resumebuilder.presentation.auth.signup.SignUpScreen
import com.example.resumebuilder.presentation.auth.signup.SignUpViewModel
import com.example.resumebuilder.presentation.bottombar.createscreen.CreateScreen
import com.example.resumebuilder.presentation.bottombar.createscreen.CreateViewModel
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeScreen
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeViewModel
import com.example.resumebuilder.presentation.bottombar.profilescreen.ProfileScreen
import com.example.resumebuilder.presentation.bottombar.profilescreen.ProfileViewModel
import com.example.resumebuilder.presentation.bottombar.routes.BottomBarScreens
import com.example.resumebuilder.presentation.onboarding.obcareerscreen.OnboardCareerScreen
import com.example.resumebuilder.presentation.onboarding.obresumescreen.OnboardResumeScreen
import com.example.resumebuilder.presentation.onboarding.obwelcomescreen.OnboardWelcomeScreen
import com.example.resumebuilder.presentation.resumebuilder.contactsummary.ContactSummaryScreen
import com.example.resumebuilder.presentation.resumebuilder.contactsummary.ContactSummaryViewModel
import com.example.resumebuilder.presentation.resumebuilder.experienceeducation.ExperienceEducationScreen
import com.example.resumebuilder.presentation.resumebuilder.experienceeducation.ExperienceEducationViewModel
import com.example.resumebuilder.presentation.resumebuilder.polishresume.PolishResumeScreen
import com.example.resumebuilder.presentation.resumebuilder.polishresume.PolishResumeViewModel
import com.example.resumebuilder.presentation.resumebuilder.resumepreview.ResumePreviewScreen
import com.example.resumebuilder.presentation.resumebuilder.resumepreview.ResumePreviewViewModel
import com.example.resumebuilder.presentation.resumebuilder.setting.SettingsScreen
import com.example.resumebuilder.presentation.resumebuilder.skillsprojects.SkillsProjectsScreen
import com.example.resumebuilder.presentation.resumebuilder.skillsprojects.SkillsProjectsViewModel
import com.example.resumebuilder.presentation.shared.navigation.handleNavigation
import com.example.resumebuilder.presentation.splash.SplashScreen
import com.example.resumebuilder.presentation.templateselect.TemplateSelectScreen
import com.example.resumebuilder.presentation.templateselect.TemplateSelectViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val startDestination = Routes.Splash
    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    NavHost(
        modifier = modifier.padding(),
        navController = navController, startDestination = startDestination
    ) {
        composable<Routes.Splash> {
            SplashScreen() {
                if (sessionManager.isLoggedIn()) {
                    navController.navigate(BottomBarScreens.Home) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                } else if (sessionManager.isOnboardingCompleted()) {
                    navController.navigate(Routes.Login) {
                        popUpTo(0) { inclusive = true }
                    }
                } else {
                    navController.navigate(Routes.OnboardingWelcomeRoute) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            }
        }
        composable<Routes.Login> {
            val viewModel: LoginViewModel = koinViewModel()
            LoginScreen(
                baseUiEvent = viewModel.baseUIEvents,
                navigation = {
                    handleNavigation(
                        action = it,
                        navController = navController
                    )
                },
                state = viewModel.state,
                actionEvent = viewModel::onEvent
            )
        }
        composable<Routes.SignUp> {
            val viewModel: SignUpViewModel = koinViewModel()
            SignUpScreen(
                baseUIEvent = viewModel.baseUIEvents,
                navigation = {
                    handleNavigation(
                        action = it,
                        navController = navController
                    )
                },
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
            )
        }
        composable<Routes.OnboardingWelcomeRoute> {
            OnboardWelcomeScreen(
                onNextClick = {
                    navController.navigate(Routes.OnboardingResumeRoute) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                },
                onSkipClick = {
                    navController.navigate(Routes.OnboardingResumeRoute) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Routes.OnboardingResumeRoute> {
            OnboardResumeScreen(
                onNextClick = {
                    navController.navigate(Routes.OnboardingCareerRoute) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                },
                onSkipClick = {
                    navController.navigate(Routes.OnboardingCareerRoute) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            )
        }
        composable<Routes.OnboardingCareerRoute> {
            OnboardCareerScreen(
                onGetStartedClick = {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.OnboardingCareerRoute) { inclusive = true }
                    }

                    sessionManager.setOnboardingCompleted()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable<BottomBarScreens.Home> {
            val viewModel = koinViewModel<HomeViewModel>()
            HomeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }
        composable<BottomBarScreens.Create> {
            val viewModel = koinViewModel<CreateViewModel>()
            CreateScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }

        composable<BottomBarScreens.Profile> {
            val viewModel = koinViewModel<ProfileViewModel>()
            ProfileScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }

        composable<Routes.CreateResume> {
            val viewModel = koinViewModel<ContactSummaryViewModel>()
            ContactSummaryScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }

        composable<Routes.ExperienceEducation> {
            val viewModel: ExperienceEducationViewModel = koinViewModel()
            ExperienceEducationScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }

        composable<Routes.SkillsProjects> {
            val viewModel: SkillsProjectsViewModel = koinViewModel()
            SkillsProjectsScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }

        composable<Routes.PolishResume> {
            val viewModel: PolishResumeViewModel = koinViewModel()
            PolishResumeScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }

        composable<Routes.TemplateSelect> { backStackEntry ->
            val route: Routes.TemplateSelect = backStackEntry.toRoute()
            val viewModel: TemplateSelectViewModel =
                koinViewModel { parametersOf(route.existingResumeId) }

            TemplateSelectScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }
        /*** 500kb size of data ( Bundle and Intent Data ) conceptually limited to 1 MB
        Exceeding this limit crashes your app with a TransactionTooLargeException ***/
        composable<Routes.ResumePreview> { backStackEntry ->
            val route: Routes.ResumePreview = backStackEntry.toRoute()
            val viewModel: ResumePreviewViewModel = koinViewModel { parametersOf(route.resumeId) }
            ResumePreviewScreen(
                state = viewModel.state,
                actionEvent = viewModel::onEvent,
                baseUiEvent = viewModel.baseUIEvents,
                navigation = { handleNavigation(it, navController) }
            )
        }
        composable<Routes.Settings> {
            SettingsScreen(
                navigation = { handleNavigation(it, navController) }
            )
        }
    }
}

//enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) }, // Right side sliding entry
//exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) }
