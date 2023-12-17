package com.buiducha.teamtracker.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.teamtracker.ui.screens.create_workspace_screen.CreateWorkspaceScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.chat_in_post_screen.ChatInPostScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.create_post_screen.CreatePostScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen.PostsScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.TaskManagerScreen
import com.buiducha.teamtracker.ui.screens.homepage_screen.HomePage
import com.buiducha.teamtracker.ui.screens.member_management.add_memeber_screen.AddMemberScreen
import com.buiducha.teamtracker.ui.screens.member_management.memeber_management_screen.MemberManagementScreen
import com.buiducha.teamtracker.ui.screens.settings_screen.SettingsScreen
import com.buiducha.teamtracker.viewmodel.PostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainGraph(
    navHostController: NavHostController
) {
    val userInfoViewModel: UserInfoViewModel = viewModel()
    val selectedWorkspaceViewModel: SelectedWorkspaceViewModel = viewModel()
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
                currentUserInfoViewModel = currentUserInfoViewModel,
                selectedWorkspaceViewModel = selectedWorkspaceViewModel
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
            CreateWorkspaceScreen(
                navController = navHostController
            )
        }
        composable(
            route = Screen.MemberManagementScreen.route
        ) {
            MemberManagementScreen(
                selectedWorkspaceViewModel = selectedWorkspaceViewModel,
                navController = navHostController
            )
        }
        composable(
            route = Screen.AddMemberScreen.route
        ) {
            AddMemberScreen(
                navController = navHostController,
                userInfoViewModel = userInfoViewModel,
                selectedWorkspaceViewModel = selectedWorkspaceViewModel
            )
        }

        composable(
            route = Screen.PostsScreen.route
        ) {
            PostsScreen(
                navController = navHostController,
                selectedWorkspaceViewModel = selectedWorkspaceViewModel
            )
        }

        composable(
            route = Screen.CreatePostScreen.route
        ) {
            CreatePostScreen(
                navController = navHostController,
                selectedWorkspaceViewModel,
            )
        }

        composable(
            route = Screen.ChatInPostScreen.route
        ) {
            ChatInPostScreen(navController = navHostController)
        }

        composable(
            route = Screen.TaskManagerScreen.route
        ) {
            TaskManagerScreen(
                navController = navHostController
            )
        }
    }
}