package com.suki.thewheel.domain.repository

import com.suki.thewheel.domain.model.WheelEntry
import kotlinx.coroutines.flow.Flow

interface EntryRepository {

    fun getEntries(): Flow<List<WheelEntry>>

    suspend fun insertEntry(wheelEntry: WheelEntry)

    suspend fun deleteEntry(wheelEntry: WheelEntry)
}