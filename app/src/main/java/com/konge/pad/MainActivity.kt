package com.konge.pad

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.konge.pad.core.Constants
import com.konge.pad.navigation.NavigationGraph
import com.konge.pad.ui.AppDrawerContent
import com.konge.pad.ui.Screen
import com.konge.pad.ui.theme.PadTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
            val navController: NavHostController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val coroutineScope = rememberCoroutineScope()
            val contextAppCompat = LocalContext.current

            PadTheme {

                Surface {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                AppDrawerContent(
                                    currentRoute = navBackStackEntry?.destination?.route
                                        ?: ""
                                ) { route ->
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }

                                    when (route) {
                                        in "${Screen.UpdateNoteScreen.route}/{${Constants.NOTE_ID}}" ->
                                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                                        in Screen.AddNoteScreen.route ->
                                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                                        else ->
                                            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING)
                                    }

                                    navController.navigate(route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            }
                        }) {
                        NavigationGraph(navController = navController, drawerState = drawerState)
                    }
                }
            }
        }
    }
}
