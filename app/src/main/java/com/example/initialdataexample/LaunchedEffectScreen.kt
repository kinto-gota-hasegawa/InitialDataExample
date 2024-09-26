package com.example.initialdataexample

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val LaunchedEffectScreenRoute = "LaunchedEffectScreenRoute"

@Composable
fun LaunchedEffectScreen(
    viewModel: LaunchedEffectViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    CommonScreen(screenState = uiState.value)
}

class LaunchedEffectViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val uiState = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.value = ScreenState.Loading
            delay(LOAD_DELAY)
            _uiState.value = ScreenState.Content("LaunchedEffectScreen")
            Log.i("hasegawa", "LaunchedEffectViewModel load DONE")
        }
    }
}