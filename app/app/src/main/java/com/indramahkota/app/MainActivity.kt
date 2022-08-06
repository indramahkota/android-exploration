package com.indramahkota.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.indramahkota.app.ui.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                navController = rememberNavController()
                SampleComposable()
            }
        }
    }

    @Composable
    fun SampleComposable() {
        Box(Modifier.fillMaxSize()) {
            Text("This text is drawn first", modifier = Modifier.align(Alignment.TopCenter))
            Box(
                Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxHeight()
                    .width(50.dp)
                    .background(Color.Blue)
            )
            Text("This text is drawn last", modifier = Modifier.align(Alignment.Center))
            FloatingActionButton(modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(12.dp),
                onClick = {}) {
                Text("+")
            }
        }
    }
}