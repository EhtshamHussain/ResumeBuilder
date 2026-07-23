package com.example.resumebuilder.presentation.shared.presentation.component.topappbar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
//    floatingActionButton: (@Composable () -> Unit)? = null,
    containerColor: Color = Color.White,
    content: @Composable (PaddingValues) -> Unit
) {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    fun debouncedClick(action: () -> Unit) {
        val now = System.currentTimeMillis()
        if (now - lastClickTime > 500L) {
            lastClickTime = now
            action()
        }
    }
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
                                debouncedClick { onNavigationClick?.invoke() }
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
                            Card(
                                modifier= Modifier
                                    .padding(end = 15.dp)
                                    .size(40.dp),
                                shape = CircleShape,
                                colors = CardDefaults.cardColors(containerColor=Color(0xFFE8F0F7))
                            ) {
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
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,

                )
            )
        },
//        floatingActionButton = {
//            floatingActionButton?.invoke()
//        },
        containerColor = containerColor,
        content = content
    )
}