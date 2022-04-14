package klt.mdy.jetpackdatastoresample.screen.navbar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import klt.mdy.jetpackdatastoresample.navigation.BottomBarDestination

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarDestination.Pref,
        BottomBarDestination.Proto
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onSurface,
        elevation = 1.dp,
    ) {
        screens.forEach { screen->
            BottomBarItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}