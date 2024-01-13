package com.buiducha.teamtracker.ui.screens.detail_workspace.posts_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.ui.screens.homepage_screen.TAG
import com.buiducha.teamtracker.ui.theme.PrimaryColor
import com.buiducha.teamtracker.viewmodel.post_viewmodel.PostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    selectedPostViewModel: SelectedPostViewModel,
    postViewModel: PostViewModel = viewModel {
        PostViewModel(
            selectedWorkspace = selectedWorkspaceViewModel,
            selectedPost = selectedPostViewModel
        )
    }
) {
    val postState by postViewModel.postsState.collectAsState()
    val scrollState = rememberLazyListState()
    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = rememberStandardBottomSheetState(
            initialValue = SheetValue.Hidden,
            skipHiddenState = false
        )
    )
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(
        sheetContent = {
            PostManagementBS(
                onDeletePost = {
                    postViewModel.deletePost()
                    scope.launch {
                        scaffoldState.bottomSheetState.hide()
                    }
                },
                onCopyPost = {

                }
            )
        },
        scaffoldState = scaffoldState
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
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(Screen.CreatePostScreen.route)
                    },
                    shape = CircleShape,
                    containerColor = PrimaryColor,
                    contentColor = Color.White
                ) {
                    Icon(
                        imageVector = Icons.Default.EditNote,
                        contentDescription = null
                    )
                }
            },
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    state = scrollState
                ) {
                    items(postState.postList) { post ->
                        PostItem(
                            post = post,
                            user = postState.userList.find { user -> user.id == post.userId }
                                ?: UserData(),
                            onViewMessage = {
                                selectedPostViewModel.postUpdate(post)
//                            navController.navigate(Screen.PostChatScreen.route)
                                val route = "${Screen.ChatScreen.route}/team:${post.id}"
                                navController.navigate(route)
                                scope.launch {
                                    scaffoldState.bottomSheetState.hide()
                                }
                            },
                            postOption = {
                                selectedPostViewModel.postUpdate(post)
                                scope.launch {
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }
                        )
                    }
                }
            }
        }
    }


    if (postState.postList.isNotEmpty()) {
        LaunchedEffect(Unit) {
            scrollState.scrollToItem(index = postState.postList.size - 1)
        }
    }

}