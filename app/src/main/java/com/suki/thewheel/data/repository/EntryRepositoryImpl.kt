package com.suki.thewheel.data.repository

import com.suki.thewheel.data.data_source.EntryDao
import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow

class EntryRepositoryImpl(private val dao: EntryDao): EntryRepository {
    override fun getEntries(): Flow<List<WheelEntry>> {
        return dao.getEntries()
    }

    override suspend fun insertEntry(wheelEntry: WheelEntry) {
        dao.insertEntry(wheelEntry)
    }

    override suspend fun deleteEntry(wheelEntry: WheelEntry) {
        dao.deleteEntry(wheelEntry)
    }
}