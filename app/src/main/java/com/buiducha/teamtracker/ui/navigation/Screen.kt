package com.buiducha.teamtracker.ui.navigation

sealed class Screen(
    val route: String
) {
//    object HomeScreen: Screen("home_screen")
//    object CalendarScreen: Screen("calendar_screen")
//    object ActivityScreen: Screen("activity_screen")
//    object SettingsScreen: Screen("settings_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object AddInfoScreen: Screen("add_info_screen")
    object CreateWSScreen: Screen("create_ws_screen")
}