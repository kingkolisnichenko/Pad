package com.konge.pad.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Unarchive
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.ColorUtils
import com.konge.pad.Destinations

@ExperimentalMaterial3Api
@Composable
fun PadTopBar(
    title: String = "",
    color: Int,
    onCLickBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(
                ColorUtils.blendARGB(
                    color,
                    0x000000,
                    0.2f
                )
            )
        ),
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
