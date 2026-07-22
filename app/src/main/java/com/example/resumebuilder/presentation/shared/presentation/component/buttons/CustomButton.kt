package com.example.resumebuilder.presentation.shared.presentation.component.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(
    device = Devices.PIXEL,
    showBackground = true
)
@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
    icon: ImageVector = Icons.AutoMirrored.Filled.ArrowForward,
    isLogin: Boolean = false,
    isWithIcon: Boolean = false,
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    ) {
    OutlinedButton(
        onClick = onClick,
        colors = if (containerColor == Color.Unspecified && contentColor == Color.Unspecified) {
            ButtonDefaults.outlinedButtonColors()
        } else {
            ButtonDefaults.outlinedButtonColors(
                containerColor = containerColor,
                contentColor = contentColor
            )
        },
        modifier = modifier
    ) {
        if (isLogin || isWithIcon) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                ) {
                Text(text, fontSize = 21.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(30.dp))
            }
        } else Text(text, fontSize = 21.sp)
    }
}
