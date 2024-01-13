package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buiducha.teamtracker.ui.theme.Blue40
import com.buiducha.teamtracker.viewmodel.CreateBoardViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel

@Composable
fun CreateBoardCard(
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    createBoardViewModel: CreateBoardViewModel = viewModel {
        CreateBoardViewModel(selectedWorkspaceViewModel)
    }
) {
    var showTextField by remember {
        mutableStateOf(false)
    }
    val createBoardState by createBoardViewModel.createBoardState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxHeight()

    ) {
        if (showTextField) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                TextField(
                    value = createBoardState.label,
                    onValueChange = {
                        createBoardViewModel.setBoardLabel(it)
                    },
                    Modifier.fillMaxWidth(0.9f)
                )

                IconButton(onClick = {
                    createBoardViewModel.createBoard(
                        onCreateSuccess = {
                            showTextField = false
                        },
                        onCreateFailure = {

                        }
                    )
                }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = "")
                }
            }
        } else {
            Box(
                modifier = Modifier
                    .background(
                        color = Blue40,
                        shape = RoundedCornerShape(8)
                    )
                    .fillMaxWidth()
                    .clickable {
                        showTextField = true
                    }
            ) {
                Text(
                    text = "+ Create Board",
                    color = Color.White,
                    fontWeight = FontWeight(500),
                    modifier = Modifier

                        .padding(16.dp)
                )
            }
        }
    }
}