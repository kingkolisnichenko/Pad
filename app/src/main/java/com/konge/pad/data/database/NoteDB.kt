package com.konge.pad.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.konge.pad.data.Note
import com.konge.pad.data.dao.NoteDao

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = false
)
abstract class NoteDB : RoomDatabase() {
    abstract val noteDao: NoteDao
}