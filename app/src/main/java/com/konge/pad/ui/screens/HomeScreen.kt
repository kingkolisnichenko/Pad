package com.konge.pad.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import dagger.hilt.android.lifecycle.HiltViewModel

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navigateToUpdateNoteScreen: (bookId: Int) -> Unit
) {
    val notes by viewModel.notes.collectAsState(
        initial = emptyList()
    )

    Box(modifier = Modifier.fillMaxSize()) {
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 16.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = { /* do something */ },

            ) {
            Icon(Icons.Filled.Add, "Add")
        }
    }
    NotesContent(
        notes = notes,
        deleteNote = { note ->
            viewModel.deleteNote(note)
        },
        navigateToUpdateNoteScreen = navigateToUpdateNoteScreen
    )

}