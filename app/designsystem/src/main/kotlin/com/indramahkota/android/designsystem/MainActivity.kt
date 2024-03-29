package com.indramahkota.android.designsystem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.indramahkota.android.core.designsystem.theme.AppTheme
import com.indramahkota.android.core.ui.splashscreen.withSlideUpAnimation
import com.indramahkota.android.designsystem.navigation.MainNavGraph

class MainActivity : ComponentActivity() {
  private lateinit var navController: NavHostController

  override fun onCreate(savedInstanceState: Bundle?) {
    installSplashScreen().withSlideUpAnimation()
    super.onCreate(savedInstanceState)

    // Turn off the decor fitting system windows, which allows us to handle insets,
    // including IME animations
    WindowCompat.setDecorFitsSystemWindows(window, false)

    setContent {
      AppTheme {
        navController = rememberNavController()
        MainNavGraph(navController = navController)
      }
    }
  }
}
