package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.TaskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskHeader

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTable(taskHeader: TaskData, rows: List<TaskData>, onAddItem: (TaskData) -> Unit) {
    val newItemTextFields = remember { mutableStateListOf("", "", "", "", "") }

    Column(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
    ) {
        /* HEADER */
        Row(modifier = Modifier.fillMaxWidth()
            .background(color = Color.LightGray)
            .padding(bottom = 2.dp)) {
            val headerRow = listOf(taskHeader.s, taskHeader.s1, taskHeader.s2, taskHeader.s3, taskHeader.s4)
            headerRow.forEachIndexed { columnIndex, cell ->
                val weight = 1f
                SimpleCell(text = cell, weight = weight)
            }
        }
        /* ROWS  */
        LazyColumn(modifier = Modifier) {
            itemsIndexed(rows) { rowIndex, taskData ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 2.dp)
                ) {
                    val row = listOf(taskData.s, taskData.s1, taskData.s2, taskData.s3, taskData.s4)
                    row.forEachIndexed { columnIndex, cell ->
                        val weight = 1f
                        SimpleCell(text = cell, weight = weight)
                    }
                }
            }
        }
        /* ADD NEW ITEM ROW */
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 2.dp)
        ) {
            newItemTextFields.forEachIndexed { index, text ->
                TextField(
                    value = text,
                    onValueChange = { newValue ->
                        newItemTextFields[index] = newValue
                    },
                    modifier = Modifier.weight(1f)
                )
            }

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
                },
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 8.dp)
            ) {
                Text(text = "Add")
            }
        }
    }
}

@Composable
private fun SimpleCell(
    text: String,
    weight: Float = 1f
) {
    val textStyle = MaterialTheme.typography.bodySmall
    val fontWidth = textStyle.fontSize.value / 2.2f // depends of font used(
    val width = (fontWidth * weight).coerceAtMost(500f)
    val textColor = MaterialTheme.colorScheme.onBackground
    Text(
        text = text,
        maxLines = 1,
        softWrap = false,
        overflow = TextOverflow.Ellipsis,
        color = textColor,
        modifier = Modifier
            .border(0.dp, textColor.copy(alpha = 0.5f))
            .fillMaxWidth()
            .width(width.dp + 100.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp)
    )
}

@Preview(showSystemUi = true)
@Composable
fun TestTaskTable() {
    SimpleTable(taskHeader = taskHeader, rows = taskData) { newItem ->
        taskData.add(newItem)
    }
}