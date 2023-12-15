package com.buiducha.teamtracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.buiducha.teamtracker.ui.screens.detail_project_screen.chat_in_post_screen.ChatInPostScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen.CreatePostScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen.PostsScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.TaskManagerScreen
import com.buiducha.teamtracker.viewmodel.MessageViewModel
import com.buiducha.teamtracker.viewmodel.PostViewModel

@Composable
fun DetailProjectScreenGraph(
    navHostController: NavHostController
){
    val postViewModel: PostViewModel = viewModel()
    val messageViewModel: MessageViewModel = viewModel()

    NavHost(navHostController, startDestination = DETAIL_PROJECT_ROUTE) {
        navigation(
            startDestination = Screen.MessageScreen.route,
            route = DETAIL_PROJECT_ROUTE){
            composable(
                route = Screen.PostsScreen.route
            ) {
                PostsScreen(
                    navController = navHostController,
                    postViewModel
                )
            }

            composable(
                route = Screen.CreatePostScreen.route
            ) {
                CreatePostScreen(navController = navHostController)
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

//            composable(
//                route = Screen.TeamMemberDetailScreen.route
//            ){
//                TeamMemberDetailScreen(
//                    navController = navHostController
//                )
//            }
        }
    }
}