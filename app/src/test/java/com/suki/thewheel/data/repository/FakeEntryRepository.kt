package com.suki.thewheel.data.repository

import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeEntryRepository : EntryRepository {

    private val entriesList =
        mutableListOf(WheelEntry("Alex", 1), WheelEntry("Brian", 2), WheelEntry("Anne", 3))
    private val entries = flowOf(entriesList)

    override fun getEntries(): Flow<List<WheelEntry>> {
        return flow { emit(entriesList) }
    }

    override suspend fun insertEntry(wheelEntry: WheelEntry) {
        entriesList.add(wheelEntry)
    }

    override suspend fun deleteEntry(wheelEntry: WheelEntry) {
        entriesList.remove(wheelEntry)
    }
}