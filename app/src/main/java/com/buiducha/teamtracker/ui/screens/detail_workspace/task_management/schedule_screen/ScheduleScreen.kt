package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.viewmodel.BoardViewModel
import com.buiducha.teamtracker.viewmodel.ScheduleViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleScreen(
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    scheduleViewModel: ScheduleViewModel = viewModel { ScheduleViewModel(selectedWorkspaceViewModel) },
    navController: NavController
) {
    val scheduleState by scheduleViewModel.scheduleState.collectAsState()

    Scaffold(
        floatingActionButton = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.super_light_blue),
                        shape = CircleShape
                    )
                    .padding(12.dp)
                    .size(28.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ZoomOut,
                    contentDescription = null
                )
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            val pagerState = rememberPagerState(
                pageCount = { scheduleState.boardList.size + 1 }
            )
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier.offset(y = 50.dp)
            ) { page ->
                if (page == pagerState.pageCount - 1) {
                    CreateBoardCard(selectedWorkspaceViewModel = selectedWorkspaceViewModel)
                } else {
                    Log.d("This is a log", scheduleState.boardList[page].toString())
                    val boardViewModel =  BoardViewModel(scheduleState.boardList[page])
                    BoardScreen(
//                        board = scheduleState.boardList[page],
                        boardViewModel = boardViewModel,
                        onTaskEdit = {
                            navController.navigate(Screen.EditTaskScreen.route)
                        }
                    )
                }
            }
        }
    }
}