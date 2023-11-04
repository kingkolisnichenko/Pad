package com.konge.pad.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.konge.pad.data.Note
import com.konge.pad.noteColor

@ExperimentalMaterial3Api
@Composable
fun NoteCard(
    note: Note,
    deleteNote: () -> Unit,
    archiveNote: () -> Unit,
    navigateToUpdateNoteScreen: (bookId: Int) -> Unit
) {
    OutlinedCard(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = noteColor(note)),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            navigateToUpdateNoteScreen(note.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Column {
                if (note.title.isNotEmpty()) {
                    Text(
                        text = note.title,
                        style = typography.headlineMedium,
                        fontWeight = FontWeight(500)
                    )
                }
                Text(
                    text = note.content,
                    style = typography.bodyLarge
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            DeleteIcon(
                deleteNote = deleteNote
            )
            if (!note.inArchive) {
                ArchiveIcon(
                    archiveNote = archiveNote
                )
            } else {
                unArchiveIcon(
                    archiveNote = archiveNote
                )
            }
        }
    }
}