package com.konge.pad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.konge.pad.navigation.NavigationGraph
import com.konge.pad.ui.AppDrawerContent
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
            val coroutineScope = rememberCoroutineScope()
            //val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

            PadTheme {

                Surface {
                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                AppDrawerContent{ route ->
                                    coroutineScope.launch {
                                        drawerState.close()
                                    }

                                    navController.navigate(route)
                                }
                            }
                        }) {
                        NavigationGraph(navController = navController, drawerState = drawerState)
                    }
                }


//                val navBackStackEntry by navController.currentBackStackEntryAsState()
//
//                bottomBarState.value = when (navBackStackEntry?.destination?.route) {
//                    Screen.AddNoteScreen.route -> false
//                    "${Screen.UpdateNoteScreen.route}/{${Constants.NOTE_ID}}" -> false
//                    else -> true
//                }
//
//                Scaffold(
//                    bottomBar = {
//                        BottomBar(
//                            navController = navController,
//                            state = bottomBarState,
//                            modifier = Modifier
//                        )
//                    },
//                    content = { paddingValues ->
//                        Column(
//                            modifier = Modifier.padding(paddingValues)
//                        ) {
//                            NavigationGraph(navController = navController, state = bottomBarState)
//                        }
//                    }
//                )
            }
        }
    }
}
