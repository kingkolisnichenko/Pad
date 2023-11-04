package com.konge.pad

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konge.pad.data.Note
import com.konge.pad.data.domain.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject
constructor(
    private val repo: NoteRepository
) : ViewModel() {
    var note by mutableStateOf(Note(0, "", "", false, DefaultLight.toArgb()))
        private set

    val notes = repo.getNotesFromRoom()
    val archiveNotes = repo.getArchiveNotesFromRoom()

    fun getNote(id: Int) = viewModelScope.launch {
        var finded = repo.getNoteFromRoom(id)
        if (finded != null) note = finded
    }

    fun addNote(note: Note) = viewModelScope.launch {
        if (note.title.isNotEmpty() or note.content.isNotEmpty())
            repo.addNoteToRoom(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repo.updateNoteInRoom(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repo.deleteNoteFromRoom(note)
    }

    fun updateTitle(title: String) {
        note = note.copy(
            title = title
        )
    }

    fun updateContent(content: String) {
        note = note.copy(
            content = content
        )
    }
    fun updateColor(color: Color) {
        note = note.copy(
            colorLight = color.toArgb()
        )
    }
    fun updateArchive(inArchive: Boolean) {
        note = note.copy(
            inArchive = inArchive
        )
    }

}