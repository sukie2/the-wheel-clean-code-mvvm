package com.suki.thewheel.presentation.wheel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.suki.thewheel.data.repository.FakeEntryRepository
import com.suki.thewheel.domain.usecase.GetEntries
import com.suki.thewheel.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WheelViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: WheelViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = WheelViewModel(getEntries = GetEntries(FakeEntryRepository()))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get all entries return non empty list`() {
        runTest{
            viewModel.getWheelEntries()
            delay(200)
            val entries = viewModel.entries.getOrAwaitValue()
            assert(entries.isNotEmpty())
        }
    }

    @Test
    fun `get random angle falls between 30 and 70`() {
        val angle = viewModel.getRandomAngle()
        assert(angle > 30 && angle < 70)
    }
}