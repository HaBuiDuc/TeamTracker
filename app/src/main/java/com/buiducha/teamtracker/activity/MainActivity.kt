package com.buiducha.teamtracker.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.navigation.AuthGraph
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.navigation.DetailProjectScreenGraph
import com.buiducha.teamtracker.ui.screens.authentication.register_screens.RegisterScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.TaskManagerScreen
import com.buiducha.teamtracker.ui.screens.homepage_screen.HomePage
import com.buiducha.speedyfood.ui.screens.root_screen.RootScreen
import com.buiducha.teamtracker.ui.theme.TeamTrackerTheme
import com.google.android.gms.tasks.Task

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TeamTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AuthGraph(navHostController = rememberNavController())
                }
            }
        }
    }
}