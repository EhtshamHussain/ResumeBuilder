package com.example.resumebuilder.presentation.shared.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

fun ViewModel.vmScopeMain(block : suspend CoroutineScope.() -> Unit): Job {
    return viewModelScope.launch {
        block()
    }
}