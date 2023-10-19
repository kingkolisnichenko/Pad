package com.konge.pad.ui

sealed class Screen(val route: String) {
    object ArchiveScreen: Screen("archive_screen")
    object HomeScreen: Screen("home_screen")
    object SettingsScreen: Screen("settings_screen")
    object UpdateNoteScreen: Screen("update_note_screen")
}
