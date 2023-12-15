package com.buiducha.teamtracker.ui.navigation

const val DETAIL_PROJECT_ROUTE = "detail_project_screen"
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
    object MessageScreen: Screen("message_screen")
    object TaskManagerScreen: Screen("task_manager_screen")
    object TeamMemberDetailScreen: Screen("team_member_detail_screen")
    object MemberManagementScreen: Screen("member_management_screen")
    object AddMemberScreen: Screen("add_member_screen")
}