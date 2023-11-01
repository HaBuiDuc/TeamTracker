package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import android.app.DatePickerDialog
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.states.width
import java.util.Calendar

@Composable
fun SimpleCell(
    text: String,
) {
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
    onDateChanged: (String) -> Unit,
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
            .padding(start = 10.dp)
            .clickable {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val selectedDate = "$year-$dayOfMonth-${month + 1}"
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
                .padding(start = 10.dp)
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