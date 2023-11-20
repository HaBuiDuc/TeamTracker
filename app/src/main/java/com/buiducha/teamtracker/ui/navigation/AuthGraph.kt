package com.buiducha.teamtracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.teamtracker.ui.screens.authentication.login_screens.LoginScreen
import com.buiducha.teamtracker.ui.screens.authentication.register_screens.RegisterScreen

@Composable
fun AuthGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginScreen.route
    ) {
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                navController = navHostController
            )
        }
        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(
                navController = navHostController
            )
        }
//        composable(
//            route = Screen.AddInfoScreen.route
//        ) {
//            AddUserInfo(
//                navController = navHostController
//            )
//        }
    }
}