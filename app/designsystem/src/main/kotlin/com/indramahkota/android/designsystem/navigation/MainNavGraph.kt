package com.indramahkota.android.designsystem.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.indramahkota.android.core.navigation.Screen
import com.indramahkota.android.feature.designsystem.navigation.designSystemCatalogNavigation

@Composable
fun MainNavGraph(navController: NavHostController) {
  NavHost(
    navController = navController,
    startDestination = Screen.DesignSystem.route,
  ) {
    designSystemCatalogNavigation(navController = navController)
  }
}
