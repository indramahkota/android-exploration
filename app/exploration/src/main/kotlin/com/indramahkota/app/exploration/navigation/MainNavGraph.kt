package com.indramahkota.app.exploration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.indramahkota.app.core.navigation.Screen
import com.indramahkota.app.feature.catalogui.navigation.catalogUINavigation
import com.indramahkota.app.feature.homebase.navigation.homeBaseNavigation
import com.indramahkota.app.feature.profile.navigation.profileNavigation
import com.indramahkota.app.feature.splash.navigation.splashNavigation

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        splashNavigation(navController = navController)
        homeBaseNavigation(navController = navController)
        profileNavigation(navController = navController)
        catalogUINavigation(navController = navController)
    }
}
