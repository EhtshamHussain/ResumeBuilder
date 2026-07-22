package com.example.resumebuilder.presentation.bottombar.testbotombar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Collections
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomNavigationBar() {
    // Total components configuration
    val barHeight = 76.dp
    val fabSize = 64.dp

    // Circle notch calculation (FAB se thoda sa bada circle background cut)
    val outerCircleSize = fabSize + 14.dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding() // Protect system navigation area
            .padding(horizontal = 1.dp, vertical = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        // 1. The main curved bar background container
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    color = Color(0xFFE2E2E2), // Main light gray bar color
                    shape = CurvedBottomBarShape()
                )
                .padding(horizontal = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Left Item: Home
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .clickable{}
            ) {
                // Customized Home icon representation
//                    color = Color(0xFF505050),
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null,
                    tint = Color(0xFF707070),
                    modifier = Modifier.size(38.dp)
                )
                Text(text = "Home", style = MaterialTheme.typography.labelSmall, color = Color(0xFF505050))
            }

            // Empty spacer area where the FAB sits above
            Spacer(modifier = Modifier.width(72.dp))

            // Right Item: Profile
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(bottom = 4.dp)
                    .clickable{}
            ) {
                // Customized Home icon representation
//                    color = Color(0xFF505050),
                Icon(
                    imageVector = Icons.Default.Collections,
                    contentDescription = null,
                    tint = Color(0xFF707070),
                    modifier = Modifier.size(38.dp)
                )
                Text(text = "Templates", style = MaterialTheme.typography.labelSmall, color = Color(0xFF505050))
            }

        }

        FloatingActionButton(
            onClick = { /* Handle Add */ },
            shape = CircleShape,
            containerColor = Color(0xFF333333), // Dark circular FAB button
            contentColor = Color.White,
            modifier = Modifier
                .offset(y = (-40).dp) // Half overlaps the curved cutout area
                .size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
