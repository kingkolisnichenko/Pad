package com.konge.pad.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.konge.pad.Destinations
import com.konge.pad.NoteViewModel
import com.konge.pad.ui.components.NotesContent
import com.konge.pad.ui.theme.Typography
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    drawerState: DrawerState,
    viewModel: NoteViewModel = hiltViewModel(),
    navigateToUpdateNoteScreen: (bookId: Int) -> Unit,
    onClickNewNote: () -> Unit
) {
    val notes by viewModel.notes.collectAsState(
        initial = emptyList()
    )

    Scaffold(
        topBar = {
            val coroutineScope = rememberCoroutineScope()

            CenterAlignedTopAppBar(
                navigationIcon = {

                    IconButton(onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }) {
                        Icon(Icons.Filled.Menu, contentDescription = "")
                    }

                },
                title = { Text(text = Destinations.Home.title) }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 8.dp),
                onClick = {
                    onClickNewNote()
                },

                ) {
                Icon(Icons.Filled.Add, "Add")
            }
        },
        content = { padding ->
            NotesContent(
                padding = padding,
                notes = notes,
                deleteNote = { note ->
                    viewModel.deleteNote(note)
                },
                archiveNote = { note ->
                    viewModel.updateNote(note.copy(inArchive = true))
                },
                navigateToUpdateNoteScreen = navigateToUpdateNoteScreen
            )
        }
    )

}