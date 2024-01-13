package com.buiducha.teamtracker.ui.navigation

sealed class Screen(
    val route: String
) {
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object ForgotPasswordScreen: Screen("forgot_password_screen")
    object AddInfoScreen: Screen("add_info_screen")
    object CreateWSScreen: Screen("create_ws_screen")
    object MessageScreen: Screen("message_screen")
    object TaskManagerScreen: Screen("task_manager_screen")
    object TeamMemberDetailScreen: Screen("team_member_detail_screen")
    object MemberManagementScreen: Screen("member_management_screen")
    object AddMemberScreen: Screen("add_member_screen")

    object PostsScreen: Screen("posts_screen")
    object PostChatScreen: Screen("chat_in_post_screen")
    object ChatScreen: Screen("chat_screen")
    // search user in chat screen
    object UserSearchScreen: Screen("user_search_screen")
    object CreatePostScreen: Screen("create_post_screen")
    object FirstSplashScreen: Screen("first_splash_screen")
    object SecondSplashScreen: Screen("second_splash_screen")
    object ThirdSplashScreen: Screen("third_splash_screen")
    object StartScreen: Screen("start_screen")
    object DetailWorkspaceScreen: Screen("detail_workspace_screen")
    object EditWorkspaceScreen: Screen("edit_workspace_screen")
    object EditTaskScreen: Screen("edit_task_screen")
    object IntroduceScreen: Screen("introduce_screen")
    object PrivacyPolicyScreen: Screen("privacy_policy_screen")
    object SearchMemberScreen: Screen("search_member_screen")
    object NotificationScreen: Screen("notification_screen")
}