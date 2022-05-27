package com.suki.thewheel.domain.usecase

import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.repository.EntryRepository
import kotlinx.coroutines.flow.Flow

class GetEntries(private val repository: EntryRepository) {

    operator fun invoke(): Flow<List<WheelEntry>> {
        return repository.getEntries()
    }
}