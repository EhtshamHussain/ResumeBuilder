package com.example.resumebuilder.presentation.bottombar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.resumebuilder.presentation.bottombar.screens.bottomBarItems
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.navigation.handleNavigation

@Composable
fun AppBottomBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination

    /**
     * Only for those screens that want bottom navigation bar
     */

    val shouldShowBottomBar = currentRoute?.hierarchy?.any { destination ->
        bottomBarItems.any { item ->
            destination.hasRoute(item.route::class)
        }
    } == true

    AnimatedVisibility(visible = shouldShowBottomBar) {
        NavigationBar {
            bottomBarItems.forEach { item ->
                val isSelected = currentRoute?.hierarchy?.any { it.hasRoute(item.route::class) } == true

                NavigationBarItem(
                    selected = isSelected,
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.label)
                    },
                    label = { Text(item.label) },
                    onClick = {
                        if (!isSelected) {
                            handleNavigation(
                                action = NavigationAction.NavigateToTab(item.route),
                                navController = navController
                            )
                        }
                    }
                )
            }
        }
    }
}