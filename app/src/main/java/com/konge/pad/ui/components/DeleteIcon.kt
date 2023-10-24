package com.konge.pad.ui.components

import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

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