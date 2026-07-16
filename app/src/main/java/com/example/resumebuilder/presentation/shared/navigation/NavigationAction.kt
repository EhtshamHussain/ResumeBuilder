package com.example.resumebuilder.presentation.shared.navigation

import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

sealed class NavigationAction {
    data object PopBackStack : NavigationAction()
    data class NavigateTo(val route: Any, val clearBackStack: Boolean = false) : NavigationAction()
    /**For bottom nav bar*/
    data class NavigateToTab(val route: Any) : NavigationAction()
    data class NavigateToClearingUpTo(
        val route: Any,
        val upToRoute: Any,
        val inclusive: Boolean = false
    ) : NavigationAction()
}

fun handleNavigation(
    action: NavigationAction,
    navController: NavHostController
) {
    when (action) {
        NavigationAction.PopBackStack -> {
            Log.d(
                "BackDebug",
                "PopBackStack — current: ${navController.currentDestination?.route}, previous: ${navController.previousBackStackEntry?.destination?.route}"
            )
            if (navController.previousBackStackEntry != null) {
                navController.popBackStack()
            }
        }

        is NavigationAction.NavigateTo -> {
            if (action.clearBackStack) {
                navController.navigate(action.route) {
//                    navController.graph.startDestinationId
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            } else {
                navController.navigate(action.route)
            }
        }

        is NavigationAction.NavigateToTab -> {
            navController.navigate(action.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }

        is NavigationAction.NavigateToClearingUpTo -> {
            navController.navigate(action.route) {
                popUpTo(action.upToRoute) { inclusive = action.inclusive }
                launchSingleTop = true
            }
        }
    }
}