package com.buiducha.teamtracker.ui.screens.homepage_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.viewmodel.HomeViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun HomePagePreview() {
//    HomePage(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    currentUserInfoViewModel: CurrentUserInfoViewModel,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsState()
    val currentUserInfo by currentUserInfoViewModel.currentUserInfo.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    var currentBottomSheet: BottomSheetScreen? by remember{
        mutableStateOf(null)
    }
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if (currentBottomSheet == BottomSheetScreen.WSManagement) {
                WSManagementBS(
                    onMemberManager = {
                        navController.navigate(Screen.MemberManagementScreen.route)
                        scope.launch {
                            homeState.selectedWorkspace?.let { selectedWorkspaceViewModel.workspaceUpdate(workspace = it) }
                            scaffoldState.bottomSheetState.hide()
                        }
                    },
                    onLeaveWorkspace = {
                        homeViewModel.leaveWorkspace()
                        scope.launch {
                            scaffoldState.bottomSheetState.hide()
                        }
                    },
                    onEditWorkspace = {

                    },
                    onDeleteWorkspace = {

                    },
                    isWorkspaceOwner = currentUserInfo.id == homeState.selectedWorkspace?.workspaceOwnerId
                )
            } else {
                WSListManagementBS(
                    onWSManagement = {},
                    onCreateWS = {
                        scope.launch {
                            scaffoldState.bottomSheetState.hide()
                        }
                        Log.d(TAG, "HomePage: onCreateWSNavigate")
                        navController.navigate(Screen.CreateWSScreen.route)

                    },
                    onFindWS = {},
                    onJoinWSById = {}
                )
            }
        },
        sheetPeekHeight = 0.dp,
        topBar = {
            HPTopBar(
                onMenuToggle = {
                    scope.launch {
                        if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
                            scaffoldState.bottomSheetState.hide()
                        } else {
                            currentBottomSheet = BottomSheetScreen.WSListManagement
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                },
                onMenuHide = {
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                },
                userData = currentUserInfo,
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
                    .height(56.dp)
            )
        },
    ) {
        if(scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
            //Create a Box with transparent color
            Log.d(TAG, "HomePage: true")
            Box(
                modifier = Modifier
                    .background(Color.Transparent)
                    .zIndex(2f)
                    .fillMaxSize()
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        scope.launch {
                            scaffoldState.bottomSheetState.hide()
                        }
                    }
            )
        }
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 16.dp,
                    bottom = 16.dp
                )
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            HPSearchBar()
            Spacer(modifier = Modifier.height(24.dp))
            WorkspacesView(
                workspaceList = homeState.workspaceList,
                onMenuToggle = {workspace ->
                    homeViewModel.setSelectedWorkspace(workspace)
                    scope.launch {
                        currentBottomSheet = BottomSheetScreen.WSManagement
                        scaffoldState.bottomSheetState.expand()
                    }
                },
                onSelectWorkspace = { workspace ->
                    homeViewModel.setSelectedWorkspace(workspace)
                },
                navController = navController
            )
        }
    }
}

const val TAG = "HomePage"