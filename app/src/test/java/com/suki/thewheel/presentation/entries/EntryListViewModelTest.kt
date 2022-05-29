package com.suki.thewheel.presentation.entries

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.suki.thewheel.data.repository.FakeEntryRepository
import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.usecase.DeleteEntry
import com.suki.thewheel.domain.usecase.GetEntries
import com.suki.thewheel.domain.usecase.InsertEntry
import com.suki.thewheel.util.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class EntryListViewModelTest {
    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: EntryListViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        val repository = FakeEntryRepository()
        viewModel = EntryListViewModel(
            getEntries = GetEntries(repository),
            deleteEntry = DeleteEntry(repository),
            insertEntry = InsertEntry(repository)
        )
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
    fun `inserting a new entry increases the count`() {
        runTest{
            viewModel.getWheelEntries()
            delay(200)
            val entriesBefore = viewModel.entries.getOrAwaitValue()
            val entriesBeforeSize = entriesBefore.size
            viewModel.insertWheelEntry("Shane")
            viewModel.getWheelEntries()
            delay(200)
            val entriesAfter = viewModel.entries.getOrAwaitValue()
            val entriesAfterSize = entriesAfter.size
            assert(entriesBeforeSize == entriesAfterSize-1)
        }
    }

    @Test
    fun `deleting a new entry decreases the count`() {
        runTest{
            viewModel.insertWheelEntry("Shane")
            viewModel.getWheelEntries()
            delay(200)
            val entriesBefore = viewModel.entries.getOrAwaitValue()
            val entriesBeforeSize = entriesBefore.size
            viewModel.deleteEntry(WheelEntry("Shane"))
            viewModel.getWheelEntries()
            delay(200)
            val entriesAfter = viewModel.entries.getOrAwaitValue()
            val entriesAfterSize = entriesAfter.size
            assert(entriesBeforeSize == entriesAfterSize+1)
        }
    }

    @Test
    fun `entry list size validation`() {
        assertFalse(viewModel.isEntryListValid(1))
        assert(viewModel.isEntryListValid(2))
        assert(viewModel.isEntryListValid(3))
    }

    @Test
    fun `limit entry size to 20`() {
        assert(viewModel.hasReachedLimit(21))
        assert(viewModel.hasReachedLimit(20))
        assertFalse(viewModel.hasReachedLimit(19))
    }
}