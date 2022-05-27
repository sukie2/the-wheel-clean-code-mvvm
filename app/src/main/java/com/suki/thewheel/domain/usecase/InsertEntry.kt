package com.suki.thewheel.domain.usecase

import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow

class InsertEntry( private val repository: EntryRepository) {

    suspend operator fun invoke(wheelEntry: WheelEntry): Flow<List<WheelEntry>> {
        return repository.getEntries()
    }
}