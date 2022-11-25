package com.indramahkota.app.exploration.navigation.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object HomePage : Screen("homepage_screen")
}
