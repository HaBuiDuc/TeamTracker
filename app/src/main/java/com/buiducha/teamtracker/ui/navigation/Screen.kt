package com.buiducha.teamtracker.ui.navigation

sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object AddInfoScreen: Screen("add_info_screen")
}