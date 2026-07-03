package com.example.resumebuilder.presentation.shared.presentation.base
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import com.example.resumebuilder.presentation.shared.presentation.theme.AppColors
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun BaseScreen(
    modifier: Modifier = Modifier,
    backGroundImg: Int? = null,
    bgColor: Color = AppColors.White57,
    baseUIEvents: SharedFlow<BaseViewModel.BaseViewModelEvents>,
    navigation: (NavigationAction) -> Unit = {},
    content: @Composable () -> Unit,
) {
    val context = LocalContext.current
    var showLoader by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        // Background layer
        if (backGroundImg != null) {
            Image(
                painter = painterResource(backGroundImg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(bgColor)
            )
        }

        // Content layer
        content()

        if (showLoader) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = AppColors.Primary
            )
        }
    }

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
                is BaseViewModel.BaseViewModelEvents.ShowLoader -> {
                    showLoader = event.show
                }
            }
        }
    }
}
