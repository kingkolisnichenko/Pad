package com.konge.pad.data.domain

import com.konge.pad.data.Note
import kotlinx.coroutines.flow.Flow

typealias Notes = List<Note>
interface NoteRepository {
    fun getNotesFromRoom(): Flow<Notes>

    suspend fun getNoteFromRoom(id: Int): Note

    suspend fun addNoteToRoom(note: Note)

    suspend fun updateNoteInRoom(note: Note)

    suspend fun deleteNoteFromRoom(note: Note)
}