package com.example.initialdataexample

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

const val ReactiveScreenRoute = "ReactiveScreenRoute"

@Composable
fun ReactiveScreen(
    viewModel: ReactiveViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    CommonScreen(screenState = uiState.value)
}


class ReactiveViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val uiState = _uiState
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ScreenState.Loading
        )

    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ScreenState.Loading
            delay(3000)
            _uiState.value = ScreenState.Content("ReactiveScreen")
        }
    }
}