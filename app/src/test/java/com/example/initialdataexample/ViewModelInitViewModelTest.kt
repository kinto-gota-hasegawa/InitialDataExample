package com.example.initialdataexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class ViewModelInitViewModelTest {

    private lateinit var viewModel: ViewModelInitViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        viewModel = ViewModelInitViewModel()
    }

    @Test
    fun `test initial state is loading`() {
        assert(viewModel.uiState.value is ScreenState.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test loaded state is content`() = runTest {
        assert(viewModel.uiState.value is ScreenState.Loading)
        advanceUntilIdle()
        assert(viewModel.uiState.value is ScreenState.Content)
    }

}