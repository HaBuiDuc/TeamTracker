package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.viewmodel.BoardViewModel

@Composable
fun BoardScreen(
//    board: Board,
//    boardViewModel: BoardViewModel = viewModel { BoardViewModel(board) },
    boardViewModel: BoardViewModel,
    onTaskEdit: (String) -> Unit,
    onTaskOption: () -> Unit
) {
    Log.d("This is a log", "BoardScreen: ")
    val boardState by boardViewModel.boardState.collectAsState()
    var isAddTask by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(),
            modifier = Modifier
                .padding(5.dp)
        ) {
            Column(Modifier.padding(10.dp)) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = boardState.board.label,
                        fontWeight = FontWeight.Bold
                    )
                    IconButton(
                        onClick = onTaskOption
                    ) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = null
                        )
                    }
                }

                boardState.taskList.forEach { task ->
                    TaskItemView(
                        task = task,
                        onTaskPressed = {
                            onTaskEdit(task.id)
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }

                if (isAddTask) {
                    TextField(
                        value = boardState.taskTitle,
                        onValueChange = {
                            boardViewModel.setTaskTitle(it)
                        },
                        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(onDone = {
                            // Handle the "submit" action here
                            Log.d("This is a log", "BoardScreen: ")
                            boardViewModel.addTask()
                        })
                    )
                } else {
                    Button(
                        onClick = {
                            isAddTask = true
                        },
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
    }
}