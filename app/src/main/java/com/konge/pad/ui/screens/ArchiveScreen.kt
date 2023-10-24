package com.konge.pad.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konge.pad.NoteViewModel
import com.konge.pad.ui.components.NotesContent
import com.konge.pad.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun ArchiveScreen(viewModel: NoteViewModel = hiltViewModel(),
                  navigateToUpdateNoteScreen: (bookId: Int) -> Unit
) {
    val archiveNotes by viewModel.archiveNotes.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        content = { padding ->
            NotesContent(
                padding = padding,
                notes = archiveNotes,
                deleteNote = { note ->
                    viewModel.deleteNote(note)
                },
                archiveNote = { note ->
                    viewModel.updateNote(note.copy(inArchive = false))
                },
                navigateToUpdateNoteScreen = navigateToUpdateNoteScreen
            )
        }
    )

}