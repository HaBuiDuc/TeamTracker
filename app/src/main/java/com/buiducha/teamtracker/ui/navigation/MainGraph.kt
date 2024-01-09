package com.buiducha.teamtracker.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.buiducha.teamtracker.ui.screens.create_workspace_screen.CreateWorkspaceScreen
import com.buiducha.teamtracker.ui.screens.detail_workspace.chat_screen.ChatScreen
import com.buiducha.teamtracker.ui.screens.detail_workspace.create_post_screen.CreatePostScreen
import com.buiducha.teamtracker.ui.screens.detail_workspace.detail_workspace_screen.DetailWorkspaceScreen
import com.buiducha.teamtracker.ui.screens.detail_workspace.posts_screen.PostsScreen
import com.buiducha.teamtracker.ui.screens.detail_workspace.search_member_screen.SearchMemberScreen
import com.buiducha.teamtracker.ui.screens.edit_workspace.EditWorkspaceScreen
import com.buiducha.teamtracker.ui.screens.homepage_screen.HomePage
import com.buiducha.teamtracker.ui.screens.member_management.add_memeber_screen.AddMemberScreen
import com.buiducha.teamtracker.ui.screens.member_management.member_management_screen.MemberManagementScreen
import com.buiducha.teamtracker.ui.screens.settings.introduce_screen.IntroduceScreen
import com.buiducha.teamtracker.ui.screens.settings.privacy_policy_screen.PrivacyPolicyScreen
import com.buiducha.teamtracker.ui.screens.settings.settings_screen.SettingsScreen
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel
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
    val selectedPostViewModel: SelectedPostViewModel = viewModel()

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
            SettingsScreen(
                navHostController,
                currentUserInfoViewModel = currentUserInfoViewModel
            )
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
                selectedWorkspaceViewModel = selectedWorkspaceViewModel,
                selectedPostViewModel = selectedPostViewModel
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
            route = Screen.ChatScreen.route
        ) {
            ChatScreen(
                navController = navHostController,
                selectedPostViewModel = selectedPostViewModel
            )
        }

        composable(
            route = Screen.DetailWorkspaceScreen.route
        ) {
            DetailWorkspaceScreen(
                navController = navHostController,
                selectedWorkspaceViewModel =selectedWorkspaceViewModel ,
                selectedPostViewModel = selectedPostViewModel
            )
        }

        composable(
            route = Screen.EditWorkspaceScreen.route
        ){
            EditWorkspaceScreen(navController = navHostController,
                selectedWorkspaceViewModel = selectedWorkspaceViewModel)
        }

        composable(
            route = Screen.IntroduceScreen.route
        ){
            IntroduceScreen(navController = navHostController)
        }

        composable(
            route = Screen.PrivacyPolicyScreen.route
        ){
            PrivacyPolicyScreen(navController = navHostController)
        }

        composable(
            route = Screen.SearchMemberScreen.route
        ){
            SearchMemberScreen(selectedWorkspaceViewModel = selectedWorkspaceViewModel, navController = navHostController)
        }
    }
}