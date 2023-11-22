package com.buiducha.teamtracker.ui.navigation

const val DETAIL_PROJECT_ROUTE = "detail_project_screen"
sealed class Screen(
    val route: String
) {
    object HomeScreen: Screen("home_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object AddInfoScreen: Screen("add_info_screen")
    object MessageScreen: Screen("message_screen")
    object TaskManagerScreen: Screen("task_manager_screen")
    object TeamMemberDetailScreen: Screen("team_member_detail_screen")
}