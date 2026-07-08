
package com.example.resumebuilder.presentation.bottombar.createscreen

data class CreateState(
    val isLoading: Boolean = false,
    val resumeTitle: String = "",
    val jobDescription: String = "",
    val error: String? = null
)