package klt.mdy.jetpackdatastoresample.screen.navbar

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import klt.mdy.jetpackdatastoresample.navigation.BottomBarDestination
import klt.mdy.jetpackdatastoresample.screen.pref.PrefScreen
import klt.mdy.jetpackdatastoresample.screen.proto.ProtoScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarDestination.Pref.route
    ) {
        composable(route = BottomBarDestination.Pref.route) {
            PrefScreen(
                scaffoldState = scaffoldState
            )
        }
        composable(route = BottomBarDestination.Proto.route) {
            ProtoScreen(
                scaffoldState = scaffoldState
            )
        }
    }
}