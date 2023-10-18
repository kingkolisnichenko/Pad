package com.konge.pad

import android.R
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@Composable
fun BottomBar(modifier: Modifier) {
    var selectedItem by remember { mutableIntStateOf(2) }

    NavigationBar(
        modifier = modifier
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    if (selectedItem == 1) Icons.Outlined.Archive else Icons.Filled.Archive,
                    contentDescription = "Archive"
                )
            },
            label = { Text("Archive") },
            selected = selectedItem == 1,
            onClick = {
                selectedItem = 1
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    if (selectedItem == 2) Icons.Outlined.Home else Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            selected = selectedItem == 2,
            onClick = {
                selectedItem = 2
            }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    if (selectedItem == 3) Icons.Outlined.Settings else Icons.Filled.Settings,
                    contentDescription = "Settings"
                )
            },
            label = { Text("Settings") },
            selected = selectedItem == 3,
            onClick = {
                selectedItem = 3
            }
        )
    }
}
