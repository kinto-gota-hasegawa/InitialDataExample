package com.example.initialdataexample

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test


class ReactiveViewModelTest {

    private lateinit var viewModel: ReactiveViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = ReactiveViewModel()
    }

    @Test
    fun `test initial state is loading`() {
        assert(viewModel.uiState.value is ScreenState.Loading)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `test loaded state is content`() = runTest {
        val results = mutableListOf<ScreenState>()
        val job = backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            viewModel.uiState.toList(results)
        }

        advanceUntilIdle()
        assert(results[0] is ScreenState.Loading)
        assert(results[1] is ScreenState.Content)

        job.cancel()
    }
}