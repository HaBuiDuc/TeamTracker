package com.buiducha.teamtracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.teamtracker.ui.screens.create_workspace_screen.CreateWorkspaceScreen
import com.buiducha.teamtracker.ui.screens.homepage_screen.HomePage
import com.buiducha.teamtracker.ui.screens.settings_screen.SettingsScreen
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel

@Composable
fun MainGraph(
    navHostController: NavHostController
) {
    val currentUserInfoViewModel: CurrentUserInfoViewModel = viewModel()
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.HomeScreen.route
    ) {
        composable(
            route = BottomBarScreen.HomeScreen.route
        ) {
            HomePage(
                navController = navHostController,
                currentUserInfoViewModel = currentUserInfoViewModel
            )
        }
        composable(
            route = BottomBarScreen.CalendarScreen.route
        ) {
        }
        composable(
            route = BottomBarScreen.ActivityScreen.route
        ) {

        }
        composable(
            route = BottomBarScreen.SettingsScreen.route
        ) {
            SettingsScreen()
        }
        composable(
            route = Screen.CreateWSScreen.route
        ) {
            CreateWorkspaceScreen(navController = navHostController)
        }
    }
}