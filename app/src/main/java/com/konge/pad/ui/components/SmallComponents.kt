package com.konge.pad.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.konge.pad.data.Note

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
fun PadBottomBar(color: Int, onClickChangeColor: () -> Unit) {

    BottomAppBar(
        modifier = Modifier.height(50.dp),
        containerColor = Color(
            ColorUtils.blendARGB(
                color,
                0x000000,
                0.2f
            )
        ),
        actions = {
            IconButton(onClick = {
                onClickChangeColor()
            }) {
                Icon(Icons.Filled.ColorLens, contentDescription = "Change color")
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun PadBottomSheet(show: Boolean, state: SheetState, onDismiss: () -> Unit, onClick: (Color) -> Unit) {
    if (show) {
        ModalBottomSheet(
            modifier = Modifier.height(170.dp),
            onDismissRequest = { onDismiss() },
            sheetState = state,
            dragHandle = null,
        ) {

            LazyRow() {
                items(Note.noteColors) { item ->
                    OutlinedCard(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(all = 14.dp),
                        shape = RoundedCornerShape(size = 25.dp),
                        border = BorderStroke(1.dp, Color.Black)
                    ) {

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(item)
                                .clickable {
                                    onClick(item)
                                }
                        )
                    }
                }
            }
        }

    }
}


