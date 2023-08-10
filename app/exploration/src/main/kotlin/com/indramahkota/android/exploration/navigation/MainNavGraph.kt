package com.indramahkota.android.exploration.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.indramahkota.android.core.navigation.Screen
import com.indramahkota.android.feature.designsystem.navigation.designSystemCatalogNavigation
import com.indramahkota.android.feature.homebase.navigation.homeBaseNavigation

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeBase.route
    ) {
        homeBaseNavigation(navController = navController)
        designSystemCatalogNavigation(navController = navController)
    }
}
