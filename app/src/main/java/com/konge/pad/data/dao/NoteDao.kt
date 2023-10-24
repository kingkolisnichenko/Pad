package com.konge.pad.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.konge.pad.data.Note
import com.konge.pad.data.domain.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM NOTES WHERE inArchive = 0 ORDER BY id ASC")
    fun getNotes(): Flow<Notes>
    @Query("SELECT * FROM NOTES WHERE inArchive ORDER BY id ASC")
    fun getArchiveNotes(): Flow<Notes>

    @Query("SELECT * FROM NOTES WHERE id = :id")
    suspend fun getNote(id: Int): Note

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}
