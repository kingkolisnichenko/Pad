package com.konge.pad

import android.content.Context
import androidx.room.Room
import com.konge.pad.data.NoteRepositoryImpl
import com.konge.pad.data.dao.NoteDao
import com.konge.pad.data.database.NoteDB
import com.konge.pad.data.domain.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideBookDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        NoteDB::class.java,
        "NOTES"
    ).build()

    @Provides
    fun provideBookDao(
        noteDb: NoteDB
    ) = noteDb.noteDao

    @Provides
    fun provideBookRepository(
        noteDao: NoteDao
    ): NoteRepository = NoteRepositoryImpl(
        noteDao = noteDao
    )
}