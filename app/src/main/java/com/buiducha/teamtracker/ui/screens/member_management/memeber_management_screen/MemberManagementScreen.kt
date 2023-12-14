package com.buiducha.teamtracker.ui.screens.member_management.memeber_management_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.ui.theme.PrimaryColor
import com.buiducha.teamtracker.viewmodel.MemberManagementViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun MemberManagementPreview() {
//    MemberManagementScreen(rememberNavController())
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemberManagementScreen(
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    memberManagementViewModel: MemberManagementViewModel = viewModel {
        MemberManagementViewModel(selectedWorkspaceViewModel)
    },
    navController: NavController
) {
    val memberManagementState by memberManagementViewModel.memberManagementState.collectAsState()
    val scope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            MemberManagementTopBar(
                onPopBack = {
                    navController.popBackStack()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.AddMemberScreen.route)
                },
                shape = CircleShape,
                containerColor = PrimaryColor,
                contentColor = Color.White
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        val pagerState = rememberPagerState(
            pageCount = { 2 }
        )
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            TabRow(
                selectedTabIndex = pagerState.currentPage
            ) {
                Tab(
                    selected = pagerState.currentPage == 0,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(0)
                        }
                    },
                    text = {
                        Text(text = "OWNERS")
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
                        Text(text = "MEMBERS")
                    }
                )
            }
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false
            ) { page ->
                if (page == 0) {
                    MemberSection(
                        memberList = mutableListOf(memberManagementState.workspaceOwner),
                        isWorkspaceOwner = memberManagementState.isWorkspaceOwner,
                        onMenuToggle = {}
                    )
                } else {
                    MemberSection(
                        memberList = memberManagementState.memberList,
                        isWorkspaceOwner = memberManagementState.isWorkspaceOwner,
                        onMenuToggle = {}
                    )
                }

            }
        }

    }
}