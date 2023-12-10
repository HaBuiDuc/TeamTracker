package com.buiducha.teamtracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.teamtracker.ui.screens.homepage_screen.HomePage
import com.buiducha.teamtracker.ui.screens.settings_screen.SettingsScreen

@Composable
fun MainGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = BottomBarScreen.HomeScreen.route
    ) {
        composable(
            route = BottomBarScreen.HomeScreen.route
        ) {
            HomePage()
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
    }
}