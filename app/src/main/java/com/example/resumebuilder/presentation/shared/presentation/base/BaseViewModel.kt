package com.example.resumebuilder.presentation.shared.presentation.base

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.example.resumebuilder.presentation.shared.extension.vmScopeMain
import com.example.resumebuilder.presentation.shared.navigation.NavigationAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {
    private val _baseUIEvents = MutableSharedFlow<BaseViewModelEvents>()
    val baseUIEvents = _baseUIEvents.asSharedFlow()

//
//    private val _sharedState = MutableStateFlow(SharedState())
//    val sharedState = _sharedState.asStateFlow()
//
//    init {
//        Log.d("BaseViewModel", "the baseViewModel recreated ")
//    }

    protected fun showError(msg: String) {
        vmScopeMain {
            _baseUIEvents.emit(BaseViewModelEvents.ShowError(msg))
        }
    }

//    protected fun updatedSharedState(
//        block: SharedState.() -> SharedState
//    ){
//        Log.d("BaseViewModel", "updatedSharedState: called ")
//        _sharedState.update { state ->
//            state.block()
//        }
//        Log.d("BaseViewModel", "updatedSharedState: ${_sharedState.value.resumeId}")
//    }

    protected fun showToast(msg: String) {
        vmScopeMain {
            _baseUIEvents.emit(BaseViewModelEvents.ShowToast(msg))
        }
    }

    protected fun navigate(route: NavigationAction) {
        vmScopeMain {
            _baseUIEvents.emit(BaseViewModelEvents.Navigate(route))
        }
    }

    protected fun navigateBack() {
        navigate(NavigationAction.PopBackStack)
    }

    sealed class BaseViewModelEvents {
        data class Navigate(val route: NavigationAction) : BaseViewModelEvents()
        data class ShowError(val msg: String) : BaseViewModelEvents()
        data class ShowToast(val msg: String) : BaseViewModelEvents()
    }
}