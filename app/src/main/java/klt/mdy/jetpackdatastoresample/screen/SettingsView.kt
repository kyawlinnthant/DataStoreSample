package klt.mdy.jetpackdatastoresample.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import klt.mdy.jetpackdatastoresample.screen.navbar.BottomBar
import klt.mdy.jetpackdatastoresample.screen.navbar.BottomNavGraph


@Composable
fun SettingView() {
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                BottomNavGraph(
                    navController = navController,
                    scaffoldState = scaffoldState
                )
            }
        }
    )
}