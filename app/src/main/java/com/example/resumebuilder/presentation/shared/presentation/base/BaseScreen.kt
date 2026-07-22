package com.example.resumebuilder.presentation.shared.presentation.base

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BaseScreen(
    baseUIEvents: SharedFlow<BaseViewModel.BaseViewModelEvents>,
    navigation: (NavigationAction) -> Unit = {},
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current

    content()
    LaunchedEffect(Unit) {
        baseUIEvents.collectLatest { event ->
            when (event) {
                is BaseViewModel.BaseViewModelEvents.ShowError -> {
                    Toast.makeText(context, event.msg, Toast.LENGTH_LONG).show()
                }

                is BaseViewModel.BaseViewModelEvents.Navigate -> {
                    navigation(event.route)
                }

                is BaseViewModel.BaseViewModelEvents.ShowToast -> {
                    Toast.makeText(context, event.msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
