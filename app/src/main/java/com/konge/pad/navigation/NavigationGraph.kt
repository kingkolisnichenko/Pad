package com.konge.pad.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.konge.pad.Destinations
import com.konge.pad.core.Constants.Companion.NOTE_ID
import com.konge.pad.ui.Screen
import com.konge.pad.ui.screens.AddNoteScreen
import com.konge.pad.ui.screens.ArchiveScreen
import com.konge.pad.ui.screens.HomeScreen
import com.konge.pad.ui.screens.SettingsScreen
import com.konge.pad.ui.screens.UpdateNoteScreen

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
            ArchiveScreen(navigateToUpdateNoteScreen = { noteId ->
                navController.navigate(
                    route = "${Screen.UpdateNoteScreen.route}/${noteId}"
                )
            })
        }
        composable(Screen.SettingsScreen.route) {
            SettingsScreen()
        }
        composable(
            route = "${Screen.UpdateNoteScreen.route}/{$NOTE_ID}",
            arguments = listOf(navArgument(NOTE_ID) {
                type = NavType.IntType
            }), enterTransition = {
                fadeIn(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
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

        composable(Screen.AddNoteScreen.route, enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing
                )
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        300, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }) {
            AddNoteScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}