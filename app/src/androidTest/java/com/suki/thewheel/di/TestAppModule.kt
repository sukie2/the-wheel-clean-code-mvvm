package com.suki.thewheel.di

import android.app.Application
import androidx.room.Room
import com.suki.thewheel.data.data_source.EntryDatabase
import com.suki.thewheel.data.repository.EntryRepositoryImpl
import com.suki.thewheel.domain.repository.EntryRepository
import com.suki.thewheel.domain.usecase.DeleteEntry
import com.suki.thewheel.domain.usecase.GetEntries
import com.suki.thewheel.domain.usecase.InsertEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object TestAppModule {
    @Provides
    @Singleton
    fun provideEntryDatabase(app: Application): EntryDatabase {
        return Room.inMemoryDatabaseBuilder(
            app,
            EntryDatabase::class.java
        ).build()
    }

    @Provides
    @Singleton
    fun provideEntryRepository(db: EntryDatabase): EntryRepository {
        return EntryRepositoryImpl(db.entryDao)
    }

    @Provides
    @Singleton
    fun provideGetEntriesUseCase(repository: EntryRepository): GetEntries {
        return GetEntries(repository)
    }

    @Provides
    @Singleton
    fun provideInsertEntryUseCase(repository: EntryRepository): InsertEntry {
        return InsertEntry(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteEntryUseCase(repository: EntryRepository): DeleteEntry {
        return DeleteEntry(repository)
    }
}