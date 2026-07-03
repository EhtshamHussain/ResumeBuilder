package com.example.resumebuilder.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resumebuilder.data.local.preference.SessionManager
import com.example.resumebuilder.presentation.auth.login.LoginScreen
import com.example.resumebuilder.presentation.auth.login.LoginViewModel
import com.example.resumebuilder.presentation.auth.signup.SignUpScreen
import com.example.resumebuilder.presentation.auth.signup.SignUpViewModel
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeScreen
import com.example.resumebuilder.presentation.onboarding.OnboardCareerScreen
import com.example.resumebuilder.presentation.onboarding.OnboardResumeScreen
import com.example.resumebuilder.presentation.onboarding.OnboardWelcomeScreen
import com.example.resumebuilder.presentation.shared.navigation.handleNavigation
import com.example.resumebuilder.presentation.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppNavigation(
    modifier: Modifier= Modifier,
    navController : NavHostController = rememberNavController(),

) {

    val startDestination = Routes.Splash

    val context = LocalContext.current
    val sessionManager = SessionManager(context)

    NavHost(
       modifier = modifier.padding(),
        navController = navController, startDestination = startDestination) {
        composable<Routes.Splash> {

            SplashScreen() {

                if (sessionManager.isLoggedIn()) {
                    navController.navigate(Routes.Home) {
                        popUpTo(Routes.Splash) {
                            inclusive = true
                        }
                    }

                } else if (sessionManager.isOnboardingCompleted()) {
                    navController.navigate(Routes.Login) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                } else {
                    navController.navigate(Routes.OnboardingWelcomeRoute) {
                        popUpTo(Routes.Splash) { inclusive = true }
                    }
                }
            }
        }
        composable<Routes.Login> {
            val viewModel : LoginViewModel = koinViewModel()
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
            val viewModel: SignUpViewModel= koinViewModel()
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
        OnboardResumeScreen (
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

        composable<Routes.Home> {
            HomeScreen()
        }
    }
}

