package com.example.initialdataexample

import androidx.compose.runtime.Composable

const val EndScreenRoute = "EndScreenRoute"

@Composable
fun EndScreen() {
    CommonScreen(screenState = ScreenState.Content("EndScreen"))
}