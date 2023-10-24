package com.konge.pad.data

import com.konge.pad.data.dao.NoteDao
import com.konge.pad.data.domain.NoteRepository
import com.konge.pad.data.domain.Notes
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(
    private val noteDao: NoteDao
) : NoteRepository{
    override fun getNotesFromRoom() = noteDao.getNotes()
    override fun getArchiveNotesFromRoom() = noteDao.getArchiveNotes()

    override suspend fun getNoteFromRoom(id: Int) = noteDao.getNote(id)

    override suspend fun addNoteToRoom(note: Note) = noteDao.addNote(note)

    override suspend fun updateNoteInRoom(note: Note) = noteDao.updateNote(note)

    override suspend fun deleteNoteFromRoom(note: Note) = noteDao.deleteNote(note)

}