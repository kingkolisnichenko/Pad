package com.konge.pad.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konge.pad.NoteViewModel
import com.konge.pad.data.Note
import com.konge.pad.ui.components.PadTopBar

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

    BackHandler {
        viewModel.updateNote(viewModel.note)
        navigateBack()
    }

    Scaffold(
        topBar = {
            PadTopBar(color = viewModel.note.color) {
                viewModel.updateNote(viewModel.note)
                navigateBack()
            }
        },
        content = { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    shape = RoundedCornerShape(8.dp),
                    value = viewModel.note.title,
                    onValueChange = {
                        viewModel.updateTitle(it)
                    },
                    placeholder = {
                        Text(
                            text = "Note title"
                        )
                    },
                    singleLine = true,
                    textStyle = typography.headlineMedium,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextField(
                    modifier = Modifier
                        .fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    value = viewModel.note.content,
                    onValueChange = {
                        viewModel.updateContent(it)
                    },
                    placeholder = {
                        Text(
                            text = "Your note ..."
                        )
                    },
                    textStyle = typography.bodyLarge,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )

            }

        }
    )


}