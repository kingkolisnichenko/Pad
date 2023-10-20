package com.konge.pad.navigation

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.konge.pad.Destinations
import com.konge.pad.core.Constants.Companion.NOTE_ID
import com.konge.pad.ui.Screen
import com.konge.pad.ui.screens.AddNoteScreen
import com.konge.pad.ui.screens.ArchiveScreen
import com.konge.pad.ui.screens.HomeScreen
import com.konge.pad.ui.screens.SettingsScreen
import com.konge.pad.ui.screens.UpdateNoteScreen
import dagger.hilt.android.qualifiers.ApplicationContext

@ExperimentalMaterial3Api
@Composable
fun NavigationGraph(navController: NavHostController) {

    NavHost(navController, startDestination = Destinations.Home.route) {

        composable(Screen.HomeScreen.route) {
            HomeScreen(
                navigateToUpdateNoteScreen = { noteId ->
                    navController.navigate(
                        route = "${Screen.UpdateNoteScreen.route}/${noteId}"
                    )
                },
                onClickNewNote = {
                    navController.navigate(
                        route = Screen.AddNoteScreen.route
                    )
                })
        }

        composable(Screen.ArchiveScreen.route) {
            ArchiveScreen()
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(
            route = "${Screen.UpdateNoteScreen.route}/{$NOTE_ID}",
            arguments = listOf(navArgument(NOTE_ID) {
                type = NavType.IntType
            })
        )
        {
            UpdateNoteScreen(
                noteId = it.arguments?.getInt(
                    "noteId"
                ) ?: 0,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.AddNoteScreen.route) {
            AddNoteScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}