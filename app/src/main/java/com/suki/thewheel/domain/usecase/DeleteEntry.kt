package com.suki.thewheel.domain.usecase

import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.repository.EntryRepository

class DeleteEntry(private val repository: EntryRepository) {

    suspend operator fun invoke(wheelEntry: WheelEntry){
        repository.deleteEntry(wheelEntry)
    }
}