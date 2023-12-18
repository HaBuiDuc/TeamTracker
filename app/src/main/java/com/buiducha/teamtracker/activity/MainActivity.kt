package com.buiducha.teamtracker.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.screens.authentication.register_screens.RegisterScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.TaskManagerScreen
import com.buiducha.teamtracker.ui.screens.homepage_screen.HomePage

import com.buiducha.teamtracker.ui.screens.root_screen.RootScreen
import com.buiducha.teamtracker.ui.theme.TeamTrackerTheme

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
    }
}