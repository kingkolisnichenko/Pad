package com.konge.pad.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
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
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konge.pad.NoteViewModel
import com.konge.pad.data.Note
import com.konge.pad.ui.components.PadBottomBar
import com.konge.pad.ui.components.PadBottomSheet
import com.konge.pad.ui.components.PadTopBar

@ExperimentalMaterial3Api
@Composable
fun UpdateNoteScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    noteId: Int,
    navigateBack: () -> Unit
) {

    val keyboard = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        viewModel.getNote(noteId)
    }

    BackHandler {
        keyboard?.hide()
        viewModel.updateNote(viewModel.note)
        navigateBack()
    }

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    Scaffold(
        containerColor = Color(viewModel.note.color),
        topBar = {
            PadTopBar{
                keyboard?.hide()
                viewModel.updateNote(viewModel.note)
                navigateBack()
            }
        },
        bottomBar = {
            PadBottomBar {
                showBottomSheet = true
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
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
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
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
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

            PadBottomSheet(
                show = showBottomSheet,
                state = modalBottomSheetState,
                containerColor = Color(viewModel.note.color),
                onDismiss = {
                    showBottomSheet = false
                },
                onClick = {
                    viewModel.updateColor(it)
                })

        }
    )


}