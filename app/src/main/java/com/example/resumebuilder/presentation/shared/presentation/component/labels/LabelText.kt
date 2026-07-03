package com.example.resumebuilder.presentation.shared.presentation.component.labels

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LabelText(
    text:String ="",
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    Text(text , fontWeight = FontWeight.W500 , fontSize = 24.sp,
    modifier = modifier , textAlign = textAlign)
}

