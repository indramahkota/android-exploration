package com.indramahkota.app.exploration.navigation.navigation

sealed class Screen(val route: String) {
    // Design Catalog
    object Catalog : Screen("design_catalog")

    object Splash : Screen("splash_screen")
    object HomePage : Screen("homepage_screen")
}
