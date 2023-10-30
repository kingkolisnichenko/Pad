package com.konge.pad.ui

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ColorLens
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalMaterial3Api
@Composable
fun BottomScreenBar(onClickChangeColor: () -> Unit) {

    BottomAppBar(
        modifier = Modifier.height(50.dp),
        actions = {
            IconButton(onClick = {
                onClickChangeColor()
            }) {
                Icon(Icons.Filled.ColorLens, contentDescription = "Change color")
            }
        }
    )
}