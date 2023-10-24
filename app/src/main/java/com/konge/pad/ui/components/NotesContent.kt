package com.konge.pad.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.konge.pad.data.Note
import com.konge.pad.data.domain.Notes

@ExperimentalMaterial3Api
@Composable
fun NotesContent(
    padding: PaddingValues,
    notes: Notes,
    deleteNote: (note: Note) -> Unit,
    archiveNote: (note: Note) -> Unit,
    navigateToUpdateNoteScreen: (noteId: Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        items(
            items = notes
        ) { note ->
            NoteCard(
                note = note,
                deleteNote = {
                    deleteNote(note)
                },
                archiveNote = {
                    archiveNote(note)
                },
                navigateToUpdateNoteScreen = navigateToUpdateNoteScreen
            )
        }
    }
}