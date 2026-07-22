package com.example.resumebuilder.presentation.bottombar.routes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

@Serializable
sealed class BottomBarScreens {

    @Serializable
    data object Home : BottomBarScreens()

    @Serializable
    data object Profile : BottomBarScreens()

    @Serializable
    data object Create : BottomBarScreens()
}


data class BottomBarItem(
    val route: BottomBarScreens,
    val label: String,
    val icon: ImageVector
)

val bottomBarItems = listOf(
    BottomBarItem(BottomBarScreens.Home, "Home", Icons.Filled.Home),
//    BottomBarItem(BottomBarScreens.Create, "Create", Icons.Filled.AddCircle),
    BottomBarItem(BottomBarScreens.Profile, "Profile", Icons.Filled.Person),
)