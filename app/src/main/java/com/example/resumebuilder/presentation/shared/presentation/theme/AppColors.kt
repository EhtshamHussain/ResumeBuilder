package com.example.resumebuilder.presentation.shared.presentation.theme
import androidx.compose.ui.graphics.Color

object AppColors {
    // Primary Brand Colors - Signature Yellow & Black
    val Primary = Color(0xFFFFE483)      // Main Yellow
    val Secondary = Color(0xFF81C784)    // Fresh Green
    val PrimaryDark100 = Color(0xFF121212) // Very Dark Grey/Black for containers
    val PrimaryLight = Color(0xFFE1D5A5)   // Mapping to Yellow for theme consistency
    
    val Background = Color(0xFF000000)   // Pure Black
    val Surface = Color(0xFF262626)      // Dark Grey for Input Fields/Cards

    val Accent = Color(0xFFFFE483)       
    
    // Text Colors
    val TextPrimary = Color(0xFFFFFFFF)  // White text
    val TextSecondary = Color(0xB3FFFFFF) // Semi-transparent white
    val TextOnPrimary = Color(0xFF000000) // Black text on Yellow elements
    val TextHint = Color(0xFF757575)
    val White57 = Color(0x91FFFFFF)      // 57% opacity white

    // Status Colors
    val Error = Color(0xFFCF6679)
    val Success = Color(0xFF4CAF50)
    
    // Others
    val DividerColor = Color(0xFF2C2C2C)
    val Yellow40 = Color(0xFFFFE483)     // Keeping for reference
}
