package com.buiducha.teamtracker.ui.screens.homepage_screen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.viewmodel.HomeViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import kotlinx.coroutines.launch

@Preview
@Composable
fun HomePagePreview() {
//    HomePage(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun HomePage(
    navController: NavController,
    currentUserInfoViewModel: CurrentUserInfoViewModel,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    homeViewModel: HomeViewModel = viewModel { HomeViewModel(currentUserInfoViewModel) },
) {
    val homeState by homeViewModel.homeState.collectAsState()
    val currentUserInfo by currentUserInfoViewModel.currentUserInfo.collectAsState()
    Log.d(TAG, "current user: ${currentUserInfo.id}")
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            skipHiddenState = false
        )
    )
    val context = LocalContext.current

    var currentBottomSheet: BottomSheetScreen? by remember {
        mutableStateOf(null)
    }
    val scope = rememberCoroutineScope()
    var isDialogVisible by remember {
        mutableStateOf(false)
    }
    val query: MutableState<String> = remember { mutableStateOf("") }

    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)


    val activityResultLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val scannedData = data?.getStringExtra("SCAN_RESULT")
            // Process the scanned data here
            Log.d(TAG, "scan data: ${data?.extras}")
            Log.d(TAG, "scan data: $scannedData")
            if (scannedData != null) {
                homeViewModel.addMemberToWorkspace(
                    workspaceId = scannedData,
                    onAddSuccess = {
                        Log.d(TAG, "HomePage: add member success")
                    }
                )
            }
        }
    }

    val barCodeLauncher =

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            if (currentBottomSheet == BottomSheetScreen.WSManagement) {
                WSManagementBS(
                    onMemberManager = {
                        navController.navigate(Screen.MemberManagementScreen.route)
                        scope.launch {
                            homeState.selectedWorkspace?.let {
                                selectedWorkspaceViewModel.workspaceUpdate(
                                    workspace = it
                                )
                            }
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
                        navController.navigate(Screen.EditWorkspaceScreen.route)
                        scope.launch {
                            homeState.selectedWorkspace?.let {
                                selectedWorkspaceViewModel.workspaceUpdate(
                                    workspace = it
                                )
                            }
                            scaffoldState.bottomSheetState.hide()
                        }
                    },
                    onDeleteWorkspace = {
                        scope.launch {
                            scaffoldState.bottomSheetState.hide()
                        }
                        isDialogVisible = true
                    },
                    isWorkspaceOwner = currentUserInfo.id == homeState.selectedWorkspace?.workspaceOwnerId,
                    onQrCodeGenerate = {
                        navController.navigate(Screen.QRScreen.route)
                        scope.launch {
                            homeState.selectedWorkspace?.let {
                                selectedWorkspaceViewModel.workspaceUpdate(
                                    workspace = it
                                )
                            }
                            scaffoldState.bottomSheetState.hide()
                        }
//                        cameraPermissionState.launchPermissionRequest()

                    }
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
                    onJoinWSByQRCode = {
                        homeViewModel.startQRScanActivity(
                            context = context as Activity,
                            activityResultLauncher = activityResultLauncher
                        )
                        scope.launch {
                            scaffoldState.bottomSheetState.hide()
                        }
                    }
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
        if (scaffoldState.bottomSheetState.currentValue == SheetValue.Expanded) {
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
        if (isDialogVisible) {
            DeleteWSConfirm(
                onConfirm = {
                    isDialogVisible = false
                    homeViewModel.deleteWorkspace()
                },
                onDismiss = {
                    isDialogVisible = false
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
            HPSearchBar(query)
            Spacer(modifier = Modifier.height(24.dp))
            WorkspacesView(
                workspaceList = homeState.workspaceList.filter {
                    it.name.contains(query.value, ignoreCase = true)
                },
                onMenuToggle = { workspace ->
                    homeViewModel.setSelectedWorkspace(workspace)
                    scope.launch {
                        currentBottomSheet = BottomSheetScreen.WSManagement
                        scaffoldState.bottomSheetState.expand()
                    }
                },
                onSelectWorkspace = { workspace ->
                    homeViewModel.setSelectedWorkspace(workspace)
                    selectedWorkspaceViewModel.workspaceUpdate(workspace = workspace)
                    Log.d(TAG, selectedWorkspaceViewModel.workspace.value.name)
                    navController.navigate(Screen.DetailWorkspaceScreen.route)
                }
            )
        }
    }
}

const val TAG = "HomePage"