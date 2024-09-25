package com.example.initialdataexample

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

sealed interface ScreenState {
    data object Loading: ScreenState
    data class Content(val title: String): ScreenState
}


@Composable
fun CommonScreen(screenState: ScreenState) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        when (screenState) {
            is ScreenState.Loading -> {
                CircularProgressIndicator()
            }
            is ScreenState.Content -> {
                Text(
                    text = screenState.title,
                    fontSize = 32.sp
                )
            }
        }
    }
}

