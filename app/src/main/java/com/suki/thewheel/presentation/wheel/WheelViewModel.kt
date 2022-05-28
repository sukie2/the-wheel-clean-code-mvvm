package com.suki.thewheel.presentation.wheel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suki.thewheel.domain.model.WheelEntry
import com.suki.thewheel.domain.usecase.GetEntries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import kotlin.random.Random.Default.nextInt

@HiltViewModel
class WheelViewModel @Inject constructor(
    private val getEntries: GetEntries
) : ViewModel() {
    private var getEntriesJob: Job? = null

    private val _entries = MutableLiveData<List<WheelEntry>>()
    val entries: LiveData<List<WheelEntry>> = _entries

    init {
        getWheelEntries()
    }

    fun getWheelEntries() {
        getEntriesJob?.cancel()
        getEntriesJob = getEntries.invoke().onEach {
            _entries.value = it
        }.launchIn(viewModelScope)
    }

    fun getRandomAngle(): Float {
        return nextInt(from = 30, until = 70).toFloat()
    }

}