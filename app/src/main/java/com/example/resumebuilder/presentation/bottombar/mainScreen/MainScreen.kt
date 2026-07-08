package com.example.resumebuilder.presentation.bottombar.mainScreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.resumebuilder.presentation.bottombar.createscreen.CreateScreen
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeScreen
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeViewModel
import com.example.resumebuilder.presentation.bottombar.profilescreen.ProfileScreen
import com.example.resumebuilder.presentation.bottombar.screens.BottomBarScreens
import com.example.resumebuilder.presentation.shared.navigation.handleNavigation
import org.koin.androidx.compose.koinViewModel
//
//@Composable
//fun MainScreen(modifier: Modifier = Modifier) {
//    val navController = rememberNavController()
//    Scaffold(modifier = Modifier.fillMaxSize(),
//        bottomBar = {
//            BottomAppBar(navController= navController , modifier = Modifier.fillMaxWidth())
//        }) {innerPadding->
//
//        NavHost(
//            navController=navController ,
//            startDestination = BottomBarScreens.Home,
//            modifier = Modifier.padding(innerPadding)
//        ){
//            composable<BottomBarScreens.Home> {
//                val viewModel = koinViewModel<HomeViewModel>()
//                HomeScreen(
//                    state = viewModel.state,
//                    actionEvent = viewModel::onEvent,
//                    baseUiEvent = viewModel.baseUIEvents,
//                    navigation = { handleNavigation(it, navController) }
//                )
//            }
//
//            composable<BottomBarScreens.Create> {
//                val viewModel = koinViewModel<HomeViewModel>()
//                CreateScreen()
//            }
//            composable<BottomBarScreens.Profile> {
//                val viewModel = koinViewModel<HomeViewModel>()
//                ProfileScreen()
//
//            }
//
//        }
//    }
//
//}
//
//
//@Composable
//fun BottomAppBar(navController : NavHostController, modifier: Modifier = Modifier) {
//    val listofScrens = listOf(BottomBarScreens.Home, BottomBarScreens.Create , BottomBarScreens.Profile)
//    NavigationBar(
//        modifier = modifier
//    ) {
//        val navBackStackEntry by navController.currentBackStackEntryAsState()
//        val currentRoute = navBackStackEntry?.destination?.route
//
//        listofScrens.forEach { Screen->
//
//            NavigationBarItem(
//                icon = {
//                    Icon(imageVector = Screen.icon, contentDescription = "image vector " )
//                },
//                label = {
//                    Text(text = Screen.label ,)
//                },
//                selected = currentRoute == Screen.route,
//                onClick = {
//                    if (currentRoute != Screen.route) {
//                        navController.navigate(Screen.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
//                    }
//                }
//            )
//
//        }
//
//    }
//}