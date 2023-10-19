package com.konge.pad.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.konge.pad.Destinations
import com.konge.pad.ui.Screen
import com.konge.pad.ui.screens.ArchiveScreen
import com.konge.pad.ui.screens.HomeScreen
import com.konge.pad.ui.screens.SettingsScreen

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = Destinations.Home.route) {
        composable(Screen.HomeScreen.route) {
            HomeScreen(navigateToUpdateNoteScreen = { noteId ->
                navController.navigate(
                    route = "${Screen.UpdateNoteScreen.route}/${noteId}"
                )
            })
        }
        composable(Screen.ArchiveScreen.route) {
            ArchiveScreen()
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
    }
}