package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.viewmodel.ScheduleViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScheduleScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    scheduleViewModel: ScheduleViewModel
) {
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
                Icon(imageVector = Icons.Default.ZoomOut, contentDescription = "")
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            val pagerState = rememberPagerState(pageCount = { 2 })
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(horizontal = 32.dp),
                modifier = Modifier.offset(y = 50.dp)
            ) { page ->
                if(page == pagerState.pageCount){

                }
                else{
                    BoardScreen()
                }

            }
        }
    }
}