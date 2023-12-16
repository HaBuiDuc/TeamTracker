package com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.viewmodel.CreatePostViewModel
import com.buiducha.teamtracker.viewmodel.PostViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

//@Preview(showSystemUi = true)
//@Composable
//fun CrPostScrPrev(){
//    CreatePostScreen(navController = rememberNavController())
//}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreatePostScreen(navController: NavController,
                     selectedWorkspaceViewModel: SelectedWorkspaceViewModel,

                     postViewModel: PostViewModel = viewModel{
                         PostViewModel(
                             selectedWorkspace = selectedWorkspaceViewModel,
                         )
                     }){
    val createPostState by postViewModel.createPostState.collectAsState()
    var content by remember { mutableStateOf("") }
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(
                    text = stringResource(id = R.string.create_workspace),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            IconButton(
                onClick = {
                    postViewModel.createPost(
                        onCreateSuccess = {
                            navController.popBackStack()
                        },
                        onCreateFailure = {
                            scope.launch {
                                snackBarHostState.showSnackbar("Can not create workspace")
                            }
                        }
                    )
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = createPostState.content,
            onValueChange = { content ->
                postViewModel.setPostContent(content)
            },
            label = { Text("Tiêu đề post") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}