package com.buiducha.teamtracker.ui.screens.create_workspace_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.theme.PurpleGrey80
import com.buiducha.teamtracker.viewmodel.workspace_viewmodel.CreateWorkspaceViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CreateWorkspacePreview() {
    CreateWorkspaceScreen(rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CreateWorkspaceScreen(
    navController: NavController,
    createWorkspaceViewModel: CreateWorkspaceViewModel = viewModel(),
) {
    val createWorkspaceState by createWorkspaceViewModel.createWorkspaceState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CreateWorkspaceTopBar(
                onPopBack = {
                    navController.popBackStack()
                },
                onSubmitCreate = {
                    createWorkspaceViewModel.createWorkspace(
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
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
            )
        },
        modifier = Modifier
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            CustomInput(
                label = stringResource(id = R.string.workspace_name),
                placeholder = stringResource(id = R.string.workspace_name_placeholder),
                value = createWorkspaceState.workspaceName,
                onValueChange = {name ->
                    createWorkspaceViewModel.setWorkspaceName(name)
                }
            )
            CustomInput(
                label = stringResource(id = R.string.description),
                placeholder = stringResource(id = R.string.workspace_des_placeholder),
                value = createWorkspaceState.workspaceDes,
                onValueChange = {des ->
                    createWorkspaceViewModel.setWorkspaceDes(des)
                }
            )

        }
    }
}

@Composable
private fun CustomInput(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = label,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )
        TextField(
            value = value,
            placeholder = {
                Text(
                    text = placeholder,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            onValueChange = {
                onValueChange(it)
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = PurpleGrey80
            ),
            modifier = Modifier
        )
    }

}