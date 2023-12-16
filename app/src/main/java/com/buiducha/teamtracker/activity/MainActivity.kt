package com.buiducha.teamtracker.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.buiducha.speedyfood.ui.screens.root_screen.RootScreen
import com.buiducha.teamtracker.ui.navigation.DetailProjectScreenGraph
import com.buiducha.teamtracker.ui.theme.TeamTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailProjectScreenGraph(navHostController = rememberNavController())
                    RootScreen()
                }
            }
        }
    }
}