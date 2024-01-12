package com.buiducha.teamtracker.ui.screens.detail_workspace.create_post_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.viewmodel.post_viewmodel.CreatePostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

//@Preview(showSystemUi = true)
//@Composable
//fun CrPostScrPrev(){
//    CreatePostScreen(navController = rememberNavController())
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePostScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    createPostViewModel: CreatePostViewModel = viewModel {
        CreatePostViewModel(
            selectedWorkspace = selectedWorkspaceViewModel,
        )
    }
) {
    val createPostState by createPostViewModel.createPostState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CreatePostTopBar(
                onCreateSubmit = {
                    createPostViewModel.createPost(
                        onCreateSuccess = {
                            navController.popBackStack()
                        },
                        onCreateFailure = {
                            scope.launch {
                                snackBarHostState.showSnackbar("Can not create workspace")
                            }
                        }
                    )
                },
                onPopBack = {
                    navController.popBackStack()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            TextField(
                value = createPostState.title,
                onValueChange = {
                    createPostViewModel.setPostTitle(it)
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.add_a_title),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()

            )
            TextField(
                value = createPostState.content,
                onValueChange = {
                    createPostViewModel.setPostContent(it)
                },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.start_a_conversation),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray
                    )
                },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}