package com.konge.pad.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konge.pad.NoteViewModel
import com.konge.pad.data.Note

@ExperimentalMaterial3Api
@Composable
fun UpdateNoteScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    noteId: Int,
    navigateBack: () -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.getNote(noteId)
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 8.dp),
            onClick = {
                viewModel.updateNote(viewModel.note)
                navigateBack()
            },

            ) {
            Icon(Icons.Filled.Save, "Save")
        }
    },
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.note.title,
                    onValueChange = {
                        viewModel.updateTitle(it)
                    },
                    placeholder = {
                        Text(
                            text = "Note title"
                        )
                    }
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = viewModel.note.content,
                    onValueChange = {
                        viewModel.updateContent(it)
                    },
                    placeholder = {
                        Text(
                            text = "Your note ..."
                        )
                    },

                    )

            }

        }
    )


}