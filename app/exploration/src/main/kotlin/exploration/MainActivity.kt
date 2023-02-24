package com.indramahkota.app.exploration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.metrics.performance.JankStats
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.indramahkota.app.core.designsystem.theme.InTheme
import com.indramahkota.app.exploration.navigation.MainNavGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// https://stackoverflow.com/questions/67891362/
// componentactivity-vs-appcompactactivity-in-android-jetpackcompose
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    /**
     * Lazily inject [JankStats], which is used to track jank throughout the app.
     */
    @Inject
    lateinit var lazyStats: dagger.Lazy<JankStats>

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            InTheme {
                navController = rememberNavController()
                MainNavGraph(navController = navController)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lazyStats.get().isTrackingEnabled = true
    }

    override fun onPause() {
        super.onPause()
        lazyStats.get().isTrackingEnabled = false
    }
}
