package com.example.initialdataexample

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

const val ViewModelInitScreenRoute = "ViewModelInitScreenRoute"

@Composable
fun ViewModelInitScreen(
    viewModel: ViewModelInitViewModel = viewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    CommonScreen(screenState = uiState.value)
}

class ViewModelInitViewModel: ViewModel() {
    private val _uiState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val uiState = _uiState.asStateFlow()
    init {
        loadData()
    }
    private fun loadData() {
        viewModelScope.launch {
            _uiState.value = ScreenState.Loading
            delay(3000)
            _uiState.value = ScreenState.Content("ViewModelInitScreen")
        }
    }
}