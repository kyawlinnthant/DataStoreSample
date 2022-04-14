package klt.mdy.jetpackdatastoresample.navigation

import klt.mdy.jetpackdatastoresample.R

sealed class BottomBarDestination(
    val route: String,
    val title: Int,
) {
    object Pref : BottomBarDestination(
        route = "pref_screen",
        title = R.string.btn_pref
    )

    object Proto : BottomBarDestination(
        route = "proto_screen",
        title = R.string.btn_proto
    )
}

