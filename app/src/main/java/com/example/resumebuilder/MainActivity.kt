package com.example.resumebuilder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.resumebuilder.presentation.bottombar.AppBottomBar
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeViewModel
import com.example.resumebuilder.presentation.navigation.AppNavigation
import com.example.resumebuilder.ui.theme.ResumeBuilderTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResumeBuilderTheme {
//                val homeViewModel : HomeViewModel = koinViewModel()
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { AppBottomBar(navController = navController ,
//                        homeViewModel
                    )
                                },
                    containerColor = Color.White,
                    contentColor = Color.Black
                ) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
            }
        }
    }
}

