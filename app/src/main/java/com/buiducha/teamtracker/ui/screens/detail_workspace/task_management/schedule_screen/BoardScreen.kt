package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.buiducha.teamtracker.data.model.project.Board
import com.buiducha.teamtracker.viewmodel.BoardViewModel
import com.buiducha.teamtracker.viewmodel.CreateBoardViewModel
import com.buiducha.teamtracker.viewmodel.ScheduleViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun BoardScreen(
    boardViewModel: BoardViewModel = viewModel(),
    board: Board
) {
    Card(
        Modifier
            .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors()
    ) {
        Column(Modifier.padding(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = board.label,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = ""
                )
            }

//            TaskItemView()

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(Color.Transparent)
            ) {
                Text(
                    text = "+ Add task",
                    color = Color.Blue
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CreateBoardCard(
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    createBoardViewModel: CreateBoardViewModel = viewModel {
        CreateBoardViewModel(selectedWorkspaceViewModel)
    }
){
    var showTextField = remember {
        mutableStateOf(false)
    }
    var createBoardState = createBoardViewModel.createBoardState.collectAsState()
//    var boardCount = boardCount

    Column(Modifier.padding(10.dp)) {
        Text(text = "+ Create Board",
            modifier = Modifier.clickable {
                showTextField.value = true
            })

            if(showTextField.value){
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    TextField(value = createBoardState.value.label,
                        onValueChange = {
                            createBoardViewModel.setBoardLabel(it)
                        },
                        Modifier.fillMaxWidth(0.9f))

                    IconButton(onClick = {
                        createBoardViewModel.createBoard(
                            onCreateSuccess = {
                                showTextField.value = false
                            },
                            onCreateFailure = {

                            }
                        )
                    }) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = "")
                    }
                }
        }
    }
}