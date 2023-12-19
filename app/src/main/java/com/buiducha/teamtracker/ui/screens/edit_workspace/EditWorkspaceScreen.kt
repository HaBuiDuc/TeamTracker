package com.buiducha.teamtracker.ui.screens.edit_workspace

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine
import com.buiducha.teamtracker.viewmodel.EditWorkspaceViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun EditWorkspacePreview() {
//    EditWorkspaceScreen(navController = rememberNavController())
}

@Composable
fun EditWorkspaceScreen(
    navController: NavController,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    editWorkspaceViewModel: EditWorkspaceViewModel = viewModel{
        EditWorkspaceViewModel(
            selectedWorkspace = selectedWorkspaceViewModel
        )
    }
) {
    var imgUrl = remember{ mutableStateOf("") }
    var uri by remember{
        mutableStateOf<Uri?>(null)
    }

    val editWorkspaceState by editWorkspaceViewModel.editWorkspaceState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val singlePhotoPicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = {
            uri = it
        }
    )

    val context = LocalContext.current

    Scaffold(
        topBar = {
            EditWorkspaceTopBar(
                onPopBack = {
                    navController.popBackStack()
                },
                onEditSubmit = {
                    editWorkspaceViewModel.setWorkspaceAvatar(imgUrl.value)
                    editWorkspaceViewModel.editWorkspace(
                        onUpdateSuccess = {
                            navController.popBackStack()
                        },
                        onUpdateFailure = {
                            scope.launch {
                                snackBarHostState.showSnackbar("Can not create workspace")
                            }
                        }
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            editWorkspaceState.workspace?.let {
                EditInputField(
                    label = "Workspace name",
                    value = it.name,
                    onValueChange = {name ->
                        editWorkspaceViewModel.setWorkspaceName(name)
                    }
                )
            }
            editWorkspaceState.workspace?.describe?.let {
                EditInputField(
                    label = stringResource(id = R.string.description),
                    value = it,
                    onValueChange = {des ->
                        editWorkspaceViewModel.setWorkspaceDes(des)
                    }
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                HorizontalLine()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            singlePhotoPicker.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.change_team_picture),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
                AsyncImage(model = uri,
                    contentDescription = null,
                    modifier = Modifier.size(248.dp))
                HorizontalLine()
                Button(onClick = {
                    uri?.let{
                        editWorkspaceViewModel.uploadImage(
                                uri=it,
                                context=context,
                                imgUrl = imgUrl,
                                oldImageUrl = editWorkspaceViewModel.editWorkspaceState.value.workspace?.avatar.toString()
                            )
                    }
                }){
                    Text("Upload")
                }

                var isUploadSuccess:String = ""
                if(imgUrl.value == ""){
                    isUploadSuccess = ""
                }else isUploadSuccess = "upload success"
                Text(
                    text = isUploadSuccess
                )
            }
        }
    }
}

@Composable
private fun EditInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
//    Text(
//        text = label,
//        fontSize = 16.sp
//    )
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = label
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

}