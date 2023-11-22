package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.detail_task_form

import android.app.DatePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.TaskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskData

import java.util.Calendar

@Preview(showSystemUi = true)
@Composable
fun ShowTaskDetail(){
    TaskDetail(taskData.get(0))
}
@Composable
fun TaskDetail(task: TaskData){
    TaskDetailScreen(task = task, onTaskUpdated = {}, onDeleteTask = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(task: TaskData, onTaskUpdated: (TaskData) -> Unit, onDeleteTask: () -> Unit) {
    val editedTaskName = remember { mutableStateOf(task.taskName) }
    val editedOwner = remember { mutableStateOf(task.owner) }
    val editedStartDay = remember { mutableStateOf(task.startDay) }
    val editedDeadline = remember { mutableStateOf(task.deadline) }
    val editedStatus = remember { mutableStateOf(task.status) }

    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = editedTaskName.value,
            onValueChange = { editedTaskName.value = it },
            label = { Text("Task Name") },
            modifier = Modifier
                .padding(8.dp)
                .width(280.dp)
                .background(Color.LightGray)
        )

        Column(modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .width(280.dp)
            .height(60.dp)){
            Text(text = "Owner",
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 14.dp, top = 5.dp))
            Text(
                text = editedOwner.value,
                modifier = Modifier
                    .padding(start = 14.dp)
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
//                userList.forEach { label ->
//                    Box(contentAlignment = Alignment.Center) {
//                        DropdownMenuItem(text = { label },
//                            onClick = {
//                                expanded = false
//                                editedOwner.value = label
//                            })
//                        Text(text = label)
//                    }
//                }
            }
        }

        Column(modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .width(280.dp)
            .height(60.dp)){
            Text(text = "Start Day",
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 14.dp, top = 5.dp))
            Text(
                text = editedStartDay.value,
                modifier = Modifier
                    .padding(start = 14.dp)
                    .clickable {
                        val calendar = Calendar.getInstance()
                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                val selectedDate = "$year-$dayOfMonth-${month + 1}"
                                editedStartDay.value = selectedDate
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
            )
        }

        Column(modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .width(280.dp)
            .height(60.dp)){
            Text(text = "Deadline",
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 14.dp, top = 5.dp))
            Text(
                text = editedDeadline.value,
                modifier = Modifier
                    .padding(start = 14.dp)
                    .clickable {
                        val calendar = Calendar.getInstance()
                        DatePickerDialog(
                            context,
                            { _, year, month, dayOfMonth ->
                                val selectedDate = "$year-$dayOfMonth-${month + 1}"
                                editedDeadline.value = selectedDate
                            },
                            calendar.get(Calendar.YEAR),
                            calendar.get(Calendar.MONTH),
                            calendar.get(Calendar.DAY_OF_MONTH)
                        ).show()
                    }
            )
        }

        Column(modifier = Modifier
            .padding(8.dp)
            .background(Color.LightGray)
            .width(280.dp)
            .height(60.dp)){
            Text(text = "Status",
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 14.dp, top = 5.dp))
            Text(
                text = editedStatus.value,
                modifier = Modifier
                    .padding(start = 14.dp)
                    .clickable { expanded = true }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
//                listStatus.forEach { label ->
//                    Box(contentAlignment = Alignment.Center) {
//                        DropdownMenuItem(text = { label },
//                            onClick = {
//                                expanded = false
//                                editedStatus.value = label
//                            })
//                        Text(text = label)
//                    }
//                }
            }
        }
        Row {
            Button(
                onClick = {
                    val updatedTask = TaskData(
                        taskName = editedTaskName.value,
                        owner = editedOwner.value,
                        startDay = editedStartDay.value,
                        deadline = editedDeadline.value,
                        status = editedStatus.value
                    )
                    onTaskUpdated(updatedTask)
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Save")
            }

            Button(
                onClick = { onDeleteTask() },
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Delete")
            }
        }
    }

}
