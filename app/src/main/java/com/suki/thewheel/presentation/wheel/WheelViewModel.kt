package com.suki.thewheel.presentation.wheel

import androidx.lifecycle.ViewModel
import com.suki.thewheel.domain.usecase.DeleteEntry
import com.suki.thewheel.domain.usecase.GetEntries
import com.suki.thewheel.domain.usecase.InsertEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WheelViewModel @Inject constructor(
    private val getEntries: GetEntries,
    private val deleteEntry: DeleteEntry,
    private val insertEntry: InsertEntry,
): ViewModel() {

}