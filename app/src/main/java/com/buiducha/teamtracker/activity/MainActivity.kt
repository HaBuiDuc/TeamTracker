package com.buiducha.teamtracker.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.navigation.AuthGraph
import com.buiducha.teamtracker.ui.navigation.MainGraph
import com.buiducha.teamtracker.ui.navigation.SplashScreenGraph
import com.buiducha.teamtracker.ui.screens.root_screen.RootScreen
import com.buiducha.teamtracker.ui.theme.TeamTrackerTheme
import com.buiducha.teamtracker.utils.CreateNotificationService

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootScreen()
                }
            }
        }
        val intent = Intent(this, CreateNotificationService::class.java)
        startService(intent)
    }
}