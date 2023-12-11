package com.buiducha.teamtracker.ui.screens.homepage_screen

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun HomePagePreview() {
    HomePage(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    navController: NavController,
    homeViewModel: HomeViewModel = viewModel(),
) {
    val homeState by homeViewModel.homeState.collectAsState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            WSManagementBS(
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
        },
        sheetPeekHeight = 0.dp,
        topBar = {
            HPTopBar(
                onMenuToggle = {
                    scope.launch {
                        scaffoldState.bottomSheetState.expand()
                    }
                },
                modifier = Modifier
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    )
            )
        },
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        scope.launch {
                            if (scaffoldState.bottomSheetState.isVisible) {
                                scaffoldState.bottomSheetState.hide()
                            }
                        }
                    },
                )
            }

    ) {
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
                workspaceList = homeState.workspaceList
            )
        }
    }
}

const val TAG = "HomePage"