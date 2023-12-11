package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputNewTaskRow(
    textFields: List<String>,
    onTextChanged: (Int, String) -> Unit
) {
    val statusState = remember { mutableStateOf(listStatus[0]) }
    val ownerState = remember {
        mutableStateOf(userList[0])
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        val text = textFields[0]
        TextField(
            value = text,
            onValueChange = { newValue ->
                onTextChanged(0, newValue) },
            modifier = Modifier
                .width(width.dp + 100.dp)
                .height(50.dp)
                .border(1.dp, Color.Blue, shape = RoundedCornerShape(5.dp))
        )


        CustomDropdownMenu(items = userList,
            selectedItem = ownerState.value,
            onItemSelected = { newItem ->
                ownerState.value = newItem
                onTextChanged(1, newItem)
            })

        repeat(2) { index ->
            CustomDatePicker(
                initialDate = textFields[index + 2],
                onDateChanged = { newDate ->
                    onTextChanged(index + 2, newDate)
                }
            )
        }

        CustomDropdownMenu(items = listStatus,
            selectedItem = statusState.value,
            onItemSelected = { newItem ->
                statusState.value = newItem
                onTextChanged(4, newItem)
            })
    }
}