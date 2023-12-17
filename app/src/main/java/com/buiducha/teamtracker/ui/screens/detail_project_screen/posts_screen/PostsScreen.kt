package com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.ui.screens.detail_project_screen.shared.DetailProjectTopBar
import com.buiducha.teamtracker.ui.theme.PrimaryColor
import com.buiducha.teamtracker.viewmodel.PostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel

@Composable
fun PostsScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    postViewModel: PostViewModel = viewModel {
        PostViewModel(
            selectedWorkspace = selectedWorkspaceViewModel
        )
    }
) {
    val postState by postViewModel.postsState.collectAsState()
    Scaffold(
        topBar = {
            DetailProjectTopBar(
                workspaceName = postViewModel.getWorkspaceName(),
                onPopBack = {
                    navController.popBackStack()
                }
            )
        },
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
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(postState.postList) { post ->
                    PostItem(
                        post = post,
                        onViewMessage = {}
                    )
                }
            }
        }
    }
}