package com.indramahkota.app.exploration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.indramahkota.app.exploration.feature.catalogui.navigation.catalogUINavigation
import com.indramahkota.app.exploration.feature.homebase.navigation.homeBaseNavigation
import com.indramahkota.app.exploration.feature.splash.navigation.splashNavigation
import com.indramahkota.app.exploration.navigation.navigation.Screen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        /**
         * Splash Module Navigation
         * */
        splashNavigation(navController = navController)

        /**
         * Home Base Module Navigation
         * */
        homeBaseNavigation(navController = navController)

        /**
         * Catalog UI Module Navigation
         * */
        catalogUINavigation(navController = navController)
    }
}
