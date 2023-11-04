package com.konge.pad.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.ColorLens
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.konge.pad.noteColors

@ExperimentalMaterial3Api
@Composable
fun PadTopBar(
    title: String = "",
    onCLickBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
//            containerColor = Color(
//                ColorUtils.blendARGB(
//                    containerColor.toArgb(),
//                    0x000000,
//                    0.1f
//                )
//            )
        ),
        navigationIcon = {
            IconButton(onClick = { onCLickBack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun PadBottomBar(onClickChangeColor: () -> Unit) {

    BottomAppBar(
        modifier = Modifier.height(50.dp),
        containerColor = Color.Transparent,
//        containerColor = Color(
//            ColorUtils.blendARGB(
//                containerColor.toArgb(),
//                0x000000,
//                0.1f
//            )
//        ),
        actions = {
            IconButton(onClick = {
                onClickChangeColor()
            }) {
                Icon(Icons.Outlined.ColorLens, contentDescription = "Change color")
            }
        }
    )
}

@ExperimentalMaterial3Api
@Composable
fun PadBottomSheet(
    show: Boolean,
    state: SheetState,
    containerColor: Color,
    onDismiss: () -> Unit,
    onClick: (Color) -> Unit
) {

    val colors = noteColors()

    AnimatedVisibility(visible = show, enter = fadeIn(), exit = fadeOut()) {
        ModalBottomSheet(
            modifier = Modifier.height(170.dp),
            onDismissRequest = { onDismiss() },
            sheetState = state,
            dragHandle = null,
            containerColor = containerColor
        ) {

            LazyRow() {
                items(colors) { item ->
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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun  Go(){
    val colors = noteColors()
        LazyRow(modifier = Modifier.background(Color.White)) {
            items(colors) { item ->
                OutlinedCard(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(all = 14.dp),
                    shape = RoundedCornerShape(size = 25.dp),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(item)
                    )
                }
            }
        }


}
