package com.konge.pad

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,

){
    object Archive : Destinations(
        route = "archive_screen",
        title = "Archive",
        icon = Icons.Outlined.Archive,
        selectedIcon = Icons.Filled.Archive
    )

    object Home : Destinations(
        route = "home_screen",
        title = "Home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home
    )

    object Settings : Destinations(
        route = "settings_screen",
        title = "Settings",
        icon = Icons.Outlined.Settings,
        selectedIcon = Icons.Filled.Settings
    )



}
