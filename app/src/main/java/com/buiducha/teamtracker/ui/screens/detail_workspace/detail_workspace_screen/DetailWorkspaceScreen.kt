package com.buiducha.teamtracker.ui.screens.detail_workspace.detail_workspace_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.screens.detail_workspace.posts_screen.PostsScreen
import com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen.ScheduleScreen
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailWorkspaceScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    selectedPostViewModel: SelectedPostViewModel
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            DetailWorkspaceTopBar(
                workspaceName = selectedWorkspaceViewModel.workspace.collectAsState().value.name,
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            val pagerState = rememberPagerState(
                pageCount = { 2 }
            )

            TabRow(
                selectedTabIndex = pagerState.currentPage,
                containerColor = Color.White
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    },
                    text = {
                        Text(text = "POSTS")
                    }
                )
                Tab(
                    selected = pagerState.currentPage == 1,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(1)
                        }
                    },
                    text = {
                        Text(text = "SCHEDULE")
                    }
                )
            }
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                if (page == 0) {
                    PostsScreen(
                        navController = navController,
                        selectedWorkspaceViewModel = selectedWorkspaceViewModel,
                        selectedPostViewModel = selectedPostViewModel
                    )
                } else if (page == 1) {
                    ScheduleScreen()
                }
            }
        }
    }
}