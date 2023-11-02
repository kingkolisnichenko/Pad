package com.konge.pad.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun DeleteIcon(
    deleteNote: () -> Unit
) {
    IconButton(
        onClick = deleteNote
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete note",
        )
    }
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

@ExperimentalMaterial3Api
@Composable
fun unArchiveIcon(
    archiveNote: () -> Unit
) {
    IconButton(
        onClick = archiveNote
    ) {
        Icon(
            imageVector = Icons.Default.Unarchive,
            contentDescription = "Unarchive note",
        )
    }
}
