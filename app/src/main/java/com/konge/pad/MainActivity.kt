package com.konge.pad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.konge.pad.core.Constants
import com.konge.pad.navigation.NavigationGraph
import com.konge.pad.ui.BottomBar
import com.konge.pad.ui.Screen
import com.konge.pad.ui.components.NotesContent
import com.konge.pad.ui.theme.PadTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val navController: NavHostController = rememberNavController()
            val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

            PadTheme {
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                bottomBarState.value = when (navBackStackEntry?.destination?.route) {
                    Screen.AddNoteScreen.route -> false
                    "${Screen.UpdateNoteScreen.route}/{${Constants.NOTE_ID}}" -> false
                    else -> true
                }

                Scaffold(
                    bottomBar = {
                        BottomBar(
                            navController = navController,
                            state = bottomBarState,
                            modifier = Modifier
                        )
                    },
                    content = { paddingValues ->
                        Column(
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            NavigationGraph(navController = navController, state = bottomBarState)
                        }
                    }
                )
            }
        }
    }
}
