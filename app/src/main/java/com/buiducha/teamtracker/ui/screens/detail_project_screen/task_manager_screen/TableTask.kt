package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.TaskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskHeader
import java.util.Calendar
import java.util.TimeZone

@OptIn(ExperimentalMaterial3Api::class)
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
                    val row = listOf(taskData.taskName, taskData.owner, taskData.startDay, taskData.deadline, taskData.status)
                    row.forEachIndexed { columnIndex, cell ->
                        val weight = 1f
                        SimpleCell(text = cell, weight = weight)
                    }
                }
            }
        }
        /* ADD NEW ITEM ROW */
        if (showNewItemRow.value) {
            RowTextField(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowTextField(
    textFields: List<String>,
    onTextChanged: (Int, String) -> Unit
) {
    val textStyle = MaterialTheme.typography.bodySmall
    val fontWidth = textStyle.fontSize.value / 2.2f
    val width = fontWidth.coerceAtMost(500f)
    val dropdownItems = listOf("Planning", "Running", "Done")
    val dropdownState = remember { mutableStateOf(dropdownItems[0]) }

    Row(modifier = Modifier.fillMaxWidth()) {
        repeat(2) { index ->
            val text = textFields[index]
            TextField(
                value = text,
                onValueChange = { newValue ->
                    onTextChanged(index, newValue)
                },
                modifier = Modifier
                    .width(width.dp + 100.dp)
                    .height(50.dp)
                    .border(1.dp, Color.Blue, shape = RoundedCornerShape(5.dp))
            )
        }

        repeat(2) { index ->
            CustomDatePicker(
                initialDate = textFields[index + 2],
                onDateChanged = { newDate ->
                    onTextChanged(index + 2, newDate)
                }
            )
        }

        CustomDropdownMenu(items = dropdownItems,
            selectedItem = dropdownState.value,
            onItemSelected = { newItem ->
                dropdownState.value = newItem
                onTextChanged(4, newItem)
            })

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
            .width(width.dp + 100.dp)
            .padding(horizontal = 4.dp, vertical = 8.dp)
    )
}

@Composable
fun CustomDatePicker(
    initialDate: String,
    onDateChanged: (String) -> Unit
) {
    val context = LocalContext.current
    val dateState = remember { mutableStateOf(initialDate) }
    val textStyle = MaterialTheme.typography.bodySmall
    val fontWidth = textStyle.fontSize.value / 2.2f // depends of font used(
    val width = fontWidth.coerceAtMost(500f)

    Text(
        text = dateState.value,
        modifier = Modifier
            .border(1.dp, Color.Blue, shape = RoundedCornerShape(5.dp))
            .width(width.dp + 100.dp)
            .height(50.dp)
            .padding(vertical = 15.dp)
            .clickable {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedDate = "$year-$dayOfMonth-${month+1}"
                        dateState.value = selectedDate
                        onDateChanged(selectedDate)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
    )
}


@Composable
fun CustomDropdownMenu(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val textStyle = MaterialTheme.typography.bodySmall
    val fontWidth = textStyle.fontSize.value / 2.2f
    val width = fontWidth.coerceAtMost(500f)

    Box {
        Text(
            text = selectedItem,
            modifier = Modifier
                .width(width.dp + 100.dp)
                .height(50.dp)
                .border(1.dp, Color.Blue, shape = RoundedCornerShape(5.dp))
                .padding(vertical = 15.dp)
                .padding(start = 5.dp)
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            items.forEach { label ->
                Box(contentAlignment = Alignment.Center) {
                    DropdownMenuItem(text = { label }, onClick = {onItemSelected(label)
                        expanded = false})
                    Text(text = label)
                }

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