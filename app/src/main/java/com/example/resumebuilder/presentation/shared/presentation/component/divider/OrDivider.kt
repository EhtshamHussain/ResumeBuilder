package com.example.resumebuilder.presentation.shared.presentation.component.divider

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(
    device = Devices.PIXEL,
    showBackground = true
)
@Composable
fun OrDivider() {
    Row(
        modifier = Modifier
            .fillMaxWidth() // Parent ki full width le raha hai
            .padding(horizontal = 16.dp), // Thoda side padding (optional)
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left Line (Align to start)
        Box(
            modifier = Modifier
                .weight(1f) // remaining space le raha hai
                .height(1.dp) // Line ki height (motai)
                .fillMaxWidth(.9f) // Aapki demand ke mutabiq 90% width
                .background(Color.LightGray.copy(alpha = 0.8f)) // Line ka color
        )

        // "OR" Text
        Text(
            text = "OR",
            color = Color.Gray,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 12.dp) // Line aur text ke beech ka gap
        )

        // Right Line (Align to end)
        Box(
            modifier = Modifier
                .weight(1f)
                .height(1.dp)
                .fillMaxWidth(0.9f)
                .background(Color.LightGray.copy(alpha = 0.8f))
        )
    }
}