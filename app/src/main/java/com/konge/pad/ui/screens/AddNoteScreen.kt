package com.konge.pad.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.konge.pad.NoteViewModel
import com.konge.pad.data.Note
import com.konge.pad.ui.components.PadBottomBar
import com.konge.pad.ui.components.PadBottomSheet
import com.konge.pad.ui.components.PadTopBar
import com.konge.pad.ui.theme.LightWhite

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    var title by rememberSaveable { mutableStateOf("") }
    var content by rememberSaveable { mutableStateOf("") }
    var color by rememberSaveable { mutableIntStateOf(LightWhite.toArgb()) }

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler {
        if (showBottomSheet) {
            showBottomSheet = false
        } else {
            viewModel.addNote(Note(0, title, content, false, color))
            navigateBack()
        }
    }

    Scaffold(

        topBar = {
            PadTopBar(color = color) {
                viewModel.addNote(Note(0, title, content, false, color))
                navigateBack()
            }
        },
        bottomBar = {
            PadBottomBar(
                color = color,
                onClickChangeColor = {
                    showBottomSheet = true
                })
        },

        content = { padding ->

            Column(
                modifier = Modifier
                    .background(Color(color))
                    .fillMaxSize()
                    .padding(padding)
                    .imePadding()
            ) {

                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent
                    ),
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Title") },
                    singleLine = true,
                    textStyle = typography.headlineMedium,
                    keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                TextField(
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onFocusChanged {
                            if (it.isFocused) {
                                keyboard?.show()
                            }
                        }
                        .fillMaxSize(),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent
                    ),
                    value = content,
                    onValueChange = { content = it },
                    placeholder = { Text("Your note...") },
                    singleLine = false,
                    textStyle = typography.bodyLarge,
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Sentences
                    )
                )

            }

            PadBottomSheet(
                show = showBottomSheet,
                state = modalBottomSheetState,
                onDismiss = { showBottomSheet = false },
                onClick = {
                    color = it.toArgb()
                })

        }
    )
}