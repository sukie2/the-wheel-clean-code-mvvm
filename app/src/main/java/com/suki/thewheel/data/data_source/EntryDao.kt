package com.suki.thewheel.data.data_source

import androidx.room.*
import com.suki.thewheel.domain.model.WheelEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface EntryDao {
    @Query("SELECT * FROM WheelEntry")
    fun getEntries(): Flow<List<WheelEntry>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(wheelEntry: WheelEntry)

    @Delete
    suspend fun deleteEntry(wheelEntry: WheelEntry)
}