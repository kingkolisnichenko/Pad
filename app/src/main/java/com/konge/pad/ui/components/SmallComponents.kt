package com.konge.pad.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.konge.pad.Destinations

@ExperimentalMaterial3Api
@Composable
fun PadTopBar(
    title: String = "",
    onCLickBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = { onCLickBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}


@ExperimentalMaterial3Api
@Composable
fun ArchiveIcon(
    archiveNote: () -> Unit
) {
    IconButton(
        onClick = archiveNote
    ) {
        Icon(
            imageVector = Icons.Default.Archive,
            contentDescription = "Archive note",
        )
    }
}