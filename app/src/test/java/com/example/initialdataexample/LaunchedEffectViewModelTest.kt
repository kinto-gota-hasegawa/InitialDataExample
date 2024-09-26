package com.example.initialdataexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class LaunchedEffectViewModelTest {


    private lateinit var viewModel: LaunchedEffectViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = LaunchedEffectViewModel()
    }

    @Test
    fun `initial state is loading`() {
        assert(viewModel.uiState.value is ScreenState.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `loaded state is content`() = runTest {
        assert(viewModel.uiState.value is ScreenState.Loading)
        viewModel.loadData()
        advanceUntilIdle()
        assert(viewModel.uiState.value is ScreenState.Content)
    }
}