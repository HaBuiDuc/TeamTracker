package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.TaskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskHeader


@Composable
fun SimpleTable(taskHeader: TaskData, rows: List<TaskData>, onAddItem: (TaskData) -> Unit) {
    val newItemTextFields = remember { mutableStateListOf("", "", "", "", "") }
    val showNewItemRow = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        /* HEADER */
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(bottom = 2.dp)) {
            val headerRow = listOf(taskHeader.taskName, taskHeader.owner, taskHeader.startDay, taskHeader.deadline, taskHeader.status)
            headerRow.forEachIndexed { columnIndex, cell ->
                SimpleCell(text = cell)
            }
        }
        /* ROWS  */
        LazyColumn(modifier = Modifier) {
            itemsIndexed(rows) { rowIndex, taskData ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                        .clickable(true, onClick = {
                            //navigation to detail task
                        })
                ) {
                    val row = listOf(taskData.taskName, taskData.owner, taskData.startDay, taskData.deadline, taskData.status)
                    row.forEachIndexed { columnIndex, cell ->
                        SimpleCell(text = cell)
                    }
                }
            }
        }
        /* ADD NEW ITEM ROW */
        if (showNewItemRow.value) {
            InputNewTaskRow(
                    textFields = newItemTextFields,
                    onTextChanged = { index, newValue ->
                        newItemTextFields[index] = newValue
                    }
                )
            Button(
                    onClick = {
                        val newItem = TaskData(
                            newItemTextFields[0],
                            newItemTextFields[1],
                            newItemTextFields[2],
                            newItemTextFields[3],
                            newItemTextFields[4]
                        )
                        onAddItem(newItem)
                        showNewItemRow.value = false
                        newItemTextFields.clear()
                        newItemTextFields.addAll(listOf("", "", "", "", ""))
                    },
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
                ) {
                    Text(text = "Add")
                }
        } else {
            Button(
                onClick = {
                    showNewItemRow.value = true
                },
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Text(text = "New Item")
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun TestTaskTable() {
    SimpleTable(taskHeader = taskHeader, rows = taskData) { newItem ->
        taskData.add(newItem)
    }
}