package com.suki.thewheel.presentation.entries

import androidx.lifecycle.*
import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.usecase.DeleteEntry
import com.suki.thewheel.domain.usecase.GetEntries
import com.suki.thewheel.domain.usecase.InsertEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EntryListViewModel @Inject constructor(
    private val getEntries: GetEntries,
    private val deleteEntry: DeleteEntry,
    private val insertEntry: InsertEntry
) : ViewModel() {
    private var getEntriesJob: Job? = null

    private val _entries = MutableLiveData<List<WheelEntry>>()
    val entries: LiveData<List<WheelEntry>> = _entries

    init {
        getWheelEntries()
    }

    fun insertWheelEntry(entryName: String) {
        viewModelScope.launch {
            insertEntry(WheelEntry(name = entryName))
        }
    }

    private fun getWheelEntries(){
        getEntriesJob?.cancel()
        getEntriesJob = getEntries.invoke().onEach {
            _entries.value = it
        }.launchIn(viewModelScope)
    }

    fun isEntryListValid(): Boolean{
        return entries.value?.size ?: 0 > 1
    }

}