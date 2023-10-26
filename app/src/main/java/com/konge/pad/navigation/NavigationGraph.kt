package com.konge.pad.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.material3.DrawerState
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
fun NavigationGraph(navController: NavHostController, drawerState: DrawerState) {

    val animationDuration = 500

    NavHost(navController, startDestination = Destinations.Home.route) {

        composable(Screen.HomeScreen.route) {

            HomeScreen(
                drawerState = drawerState,
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
            ArchiveScreen(
                drawerState = drawerState,
                navigateToUpdateNoteScreen = { noteId ->
                    navController.navigate(
                        route = "${Screen.UpdateNoteScreen.route}/${noteId}"
                    )
                })
        }

        composable(Screen.SettingsScreen.route) {
            SettingsScreen(
                drawerState = drawerState
            )
        }

        composable(
            route = "${Screen.UpdateNoteScreen.route}/{$NOTE_ID}",
            arguments = listOf(navArgument(NOTE_ID) {
                type = NavType.IntType
            }),
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(animationDuration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(animationDuration)
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

        composable(
            Screen.AddNoteScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                    animationSpec = tween(animationDuration)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(animationDuration)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                    animationSpec = tween(animationDuration)
                )
            }
        ) {
            AddNoteScreen(navigateBack = {
                navController.popBackStack()
            })
        }
    }
}