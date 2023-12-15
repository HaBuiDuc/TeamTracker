package com.buiducha.teamtracker.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.navigation.AuthGraph
import com.buiducha.teamtracker.ui.theme.TeamTrackerTheme

class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamTrackerTheme {
                AuthGraph(navHostController = rememberNavController())
            }
        }
    }
}