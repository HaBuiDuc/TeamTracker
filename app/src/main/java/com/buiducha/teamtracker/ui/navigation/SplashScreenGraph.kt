package com.buiducha.teamtracker.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.teamtracker.ui.screens.splash_screen.FirstSplashScreen
import com.buiducha.teamtracker.ui.screens.splash_screen.SecondSplashScreen
import com.buiducha.teamtracker.ui.screens.splash_screen.ThirdSplashScreen




@Composable
fun SplashScreenGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.FirstSplashScreen.route
    ) {
        composable(route =Screen.FirstSplashScreen.route){
            FirstSplashScreen(navController = navHostController)
        }
        composable(route =Screen.SecondSplashScreen.route){
            SecondSplashScreen(navController = navHostController)
        }
        composable(route =Screen.ThirdSplashScreen.route){
            ThirdSplashScreen(navController = navHostController)
        }
    }
}
