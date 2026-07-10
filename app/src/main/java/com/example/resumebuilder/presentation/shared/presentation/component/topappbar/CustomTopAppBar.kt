package com.example.resumebuilder.presentation.shared.presentation.component.topappbar


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,

    title: String,

    navigationIcon: ImageVector? = null,
    onNavigationClick: (() -> Unit)? = null,

    actionIcon: ImageVector? = null,
    onActionClick: (() -> Unit)? = null,

    floatingActionButton: (@Composable () -> Unit)? = null,

    containerColor: Color = Color.White,

    content: @Composable (PaddingValues) -> Unit
) {

    Scaffold(
        modifier = modifier.fillMaxSize(),

        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF005EA4)
                    )
                },

                navigationIcon = {
                    navigationIcon?.let {

                        IconButton(
                            onClick = {
                                onNavigationClick?.invoke()
                            }
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = Color(0xFF005EA4)
                            )
                        }
                    }
                },

                actions = {

                    actionIcon?.let {

                        IconButton(
                            onClick = {
                                onActionClick?.invoke()
                            }
                        ) {
                            Icon(
                                imageVector = it,
                                contentDescription = null,
                                tint = Color(0xFF005EA4)
                            )
                        }
                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },

        floatingActionButton = {
            floatingActionButton?.invoke()
        },

        containerColor = containerColor,

        content = content
    )
}