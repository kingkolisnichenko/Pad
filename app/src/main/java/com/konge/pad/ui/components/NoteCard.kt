package com.konge.pad.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.konge.pad.data.Note
import com.konge.pad.ui.theme.Typography

@ExperimentalMaterial3Api
@Composable
fun NoteCard(
    note: Note,
    deleteNote: () -> Unit,
    navigateToUpdateNoteScreen: (bookId: Int) -> Unit
) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                start = 8.dp,
                end = 8.dp,
                top = 4.dp,
                bottom = 4.dp
            )
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(3.dp),
        onClick = {
            navigateToUpdateNoteScreen(note.id)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    text = note.title,
                    style = Typography.titleLarge,
                    fontSize = 14.sp
                )
                Text(
                    text = note.content,
                    fontSize = 12.sp
                )
            }
            Spacer(
                modifier = Modifier.weight(1f)
            )
            DeleteIcon(
                deleteNote = deleteNote
            )
        }
    }
}