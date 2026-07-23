package com.example.resumebuilder.presentation.bottombar

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.resumebuilder.presentation.bottombar.homescreen.HomeViewModel
import com.example.resumebuilder.presentation.bottombar.routes.bottomBarItems
import com.example.resumebuilder.presentation.bottombar.testbotombar.DynamicNotchBottomBarShape
import com.example.resumebuilder.presentation.navigation.Routes
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.navigation.handleNavigation

@Composable
fun AppBottomBar(
    navController: NavHostController, homeViewModel: HomeViewModel
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    val bottomBarShape = remember { DynamicNotchBottomBarShape() }

    val shouldShowBottomBar = currentRoute?.hierarchy?.any { destination ->
        bottomBarItems.any { item -> destination.hasRoute(item.route::class) }
    } == true
    AnimatedVisibility(
        visible = shouldShowBottomBar,
        enter = EnterTransition.None,
        exit = ExitTransition.None
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(horizontal = 1.dp, vertical = 8.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        color = Color(0xFFE2E2E2),
                        shape = bottomBarShape
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                bottomBarItems.forEachIndexed { index, item ->
                    val isSelected = currentRoute?.hierarchy?.any { it.hasRoute(item.route::class) } == true
                    val contentColor by animateColorAsState(
                        targetValue = if (isSelected) Color(0xFF2D2D2D) else Color(0xFF757575),
                        label = "iconColor"
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) {
                                Log.d("TAG", "AppBottomBar: ${currentRoute } routes ${item.route} index ${index} " )
                                if (!isSelected) {
                                    handleNavigation(
                                        action = NavigationAction.NavigateToTab(item.route),
                                        navController = navController
                                    )
                                }
                            }
                    ) {
                        val indicatorWidth by animateDpAsState(
                            targetValue = if (isSelected) 36.dp else 0.dp,
                            label = "indicator"
                        )
                        Box(
                            modifier = Modifier
                                .width(indicatorWidth)
                                .height(4.dp)
                                .clip(RoundedCornerShape(2.dp))
                                .background(Color(0xFF2D2D2D))
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.label,
                            tint = contentColor,
                            modifier = Modifier.size(28.dp)
                        )

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = item.label,
                            style = MaterialTheme.typography.labelSmall,
                            color = contentColor
                        )
                    }

                    if (index == 0) {
                        Spacer(modifier = Modifier.width(116.dp))
                    }
                }
            }

            val isCreateSelected = currentRoute?.hierarchy?.any { it.hasRoute(Routes.CreateResume::class) } == true

            FloatingActionButton(
                onClick = {
                    if (!isCreateSelected) {
                        homeViewModel.
                        handleNavigation(
                            action = NavigationAction.NavigateTo(Routes.CreateResume),
                            navController = navController
                        )
                    }
                },
                shape = CircleShape,
                containerColor = if (isCreateSelected) Color(0xFF505050) else Color(0xFF333333),
                contentColor = Color.White,
                elevation = FloatingActionButtonDefaults.elevation(0.dp, 0.dp),
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-20).dp)
                    .size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Action",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

