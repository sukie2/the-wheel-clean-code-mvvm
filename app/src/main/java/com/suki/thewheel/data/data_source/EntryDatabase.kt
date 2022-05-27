package com.suki.thewheel.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suki.thewheel.domain.model.WheelEntry

@Database(
    entities = [WheelEntry::class],
    version = 1
)
abstract class EntryDatabase: RoomDatabase() {
    abstract val entryDao: EntryDao

    companion object {
        const val DATABASE_NAME = "entry_db"
    }
}