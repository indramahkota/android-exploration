package com.indramahkota.android.exploration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.indramahkota.android.core.navigation.Screen
import com.indramahkota.android.feature.catalogui.navigation.catalogUINavigation
import com.indramahkota.android.feature.homebase.navigation.homeBaseNavigation
import com.indramahkota.android.feature.profile.navigation.profileNavigation
import com.indramahkota.android.feature.splash.navigation.splashNavigation

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