package com.konge.pad.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.isSystemInDarkTheme
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
import com.konge.pad.DefaultDark
import com.konge.pad.DefaultLight
import com.konge.pad.NoteViewModel
import com.konge.pad.data.Note
import com.konge.pad.ui.components.PadBottomBar
import com.konge.pad.ui.components.PadBottomSheet
import com.konge.pad.ui.components.PadTopBar


@ExperimentalMaterial3Api
@Composable
fun AddNoteScreen(
    viewModel: NoteViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val keyboard = LocalSoftwareKeyboardController.current

    var _noteTitle by rememberSaveable { mutableStateOf("") }
    var _noteContent by rememberSaveable { mutableStateOf("") }
    var _noteColorLight by remember { mutableStateOf(DefaultLight) }
    var _noteColorDark by remember { mutableStateOf(DefaultDark) }

    var showBottomSheet by rememberSaveable { mutableStateOf(false) }
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BackHandler {
        if (showBottomSheet) {
            showBottomSheet = false
        } else {
            keyboard?.hide()
            viewModel.addNote(Note(0, _noteTitle, _noteContent, false, _noteColorLight.toArgb()))
            navigateBack()
        }
    }

    Scaffold(
        modifier = Modifier
            .imePadding(),
        containerColor = _noteColorLight,
        topBar = {
            PadTopBar {
                keyboard?.hide()
                viewModel.addNote(Note(0, _noteTitle, _noteContent, false, _noteColorLight.toArgb()))
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
                    .imePadding()
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
                    value = _noteTitle,
                    onValueChange = { _noteTitle = it },
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
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    value = _noteContent,
                    onValueChange = { _noteContent = it },
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
                containerColor = _noteColorLight,
                onDismiss = {
                    showBottomSheet = false
                    focusRequester.requestFocus()
                },
                onClick = {
                    _noteColorLight = it
                })

        }
    )
}