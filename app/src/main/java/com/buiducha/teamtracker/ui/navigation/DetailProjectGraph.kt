package com.buiducha.teamtracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
//import com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen.MessageScreen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.TaskManagerScreen
//import com.buiducha.teamtracker.ui.screens.detail_project_screen.team_member_detail.TeamMemberDetailScreen

@Composable
fun DetailProjectScreenGraph(
    navHostController: NavHostController
){
    NavHost(navHostController, startDestination = DETAIL_PROJECT_ROUTE) {
        navigation(
            startDestination = Screen.MessageScreen.route,
            route = DETAIL_PROJECT_ROUTE){
//            composable(
//                route = Screen.MessageScreen.route
//            ) {
//                MessageScreen(
//                    navController = navHostController
//                )
//            }
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