package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.create_task_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.More
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine
import com.buiducha.teamtracker.ui.screens.detail_workspace.task_management._share.BoxTagColor
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//Có 3 composable: CreateTaskScreen, TaskDateRangePicker và DropDownMenuTag
// TaskDateRangePicker: chọn ngày tháng bắt đầu và kết thúc
// DropDownMenuTag: Chọn tag

@Preview(showSystemUi = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateTaskScreen(){

    val taskName = remember{ mutableStateOf("") }
    val taskTag = remember { mutableStateOf(0)}

    val startDate = remember {mutableLongStateOf(System.currentTimeMillis()) }
    val endDate = remember {mutableLongStateOf(System.currentTimeMillis())}

    //state quản lý mở/đóng các picker và dropdown menu
    val expanded = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CreateTaskTopBar()
        }
    ) {
        Column(
            Modifier
                .offset(y = 50.dp)
                .padding(10.dp)) {
            Column {
                Text(text = "Tag 1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Row {
                    Text(text = "Table A", fontWeight = FontWeight.Bold)
                    Text(text = "  -  ")
                    Text(text = "List B", fontWeight = FontWeight.Bold)
                }
                HorizontalLine(Modifier.padding(0.dp, 10.dp),
                    width = 1.2)
            }

// Nhập tên task
            TextField(value = taskName.value,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {taskName.value = it},
                label = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.DriveFileRenameOutline,
                            contentDescription = "")
                        Text("Task Name")
                    }
                })

//=======================================================================
// Chọn status (running, planning, done)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Status")
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .clickable { expanded.value = true }
                    .background(color = Color.LightGray),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.More,
                    contentDescription = "",
                    modifier = Modifier.padding(15.dp)
                    )
                BoxTagColor(taskTag = taskTag.value)
                DropDownMenuTag(expanded = expanded, taskTag = taskTag)
            }

//=======================================================================
// Thêm thành viên
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Responsible member")
            Row(
                Modifier
                    .height(60.dp)
                    .background(color = Color.LightGray)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "",
                    modifier = Modifier.padding(15.dp))

                //danh sách các thành viên tham gia nhiệm vụ
                //===============
                Row{
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.super_light_blue),
                                shape = CircleShape
                            )
                            .padding(12.dp)
                            .size(28.dp)
                    ) {
                        Text(
                            text = "CN",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                //==================

                Button(onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Icon(imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        tint = Color.Blue
                    )
                }
            }

//=======================================================================
// Chọn thời gian bắt đầu, kết thúc
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Select start and end date")
            Row(
                Modifier
                    .height(50.dp)
                    .background(color = Color.LightGray)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically){
                TaskDateRangePicker(startDate = startDate, endDate = endDate)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDateRangePicker(
    startDate: MutableState<Long>,
    endDate: MutableState<Long>
){

    var startDate = startDate
    var endDate = endDate

    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = startDate.value,
        initialSelectedEndDateMillis = endDate.value
    )

    var showDateRangePicker by remember {
        mutableStateOf(false)
    }

    if (showDateRangePicker) {
        DatePickerDialog(
            onDismissRequest = {
                showDateRangePicker = false
            },
            confirmButton = {
                TextButton(onClick = {
                    showDateRangePicker = false
                    startDate.value = dateRangePickerState.selectedStartDateMillis!!
                    endDate.value = dateRangePickerState.selectedEndDateMillis!!
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDateRangePicker = false
                }) {
                    Text(text = "Cancel")
                }
            }
        ) {
            DateRangePicker(
                state = dateRangePickerState,
                modifier = Modifier.fillMaxSize(0.9f)
            )
        }
    }

    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
    TextButton(onClick = {showDateRangePicker = true}) {
        Text(text = "${formatter.format(Date(startDate.value))} - ${formatter.format(Date(endDate.value))}")
    }
}

@Composable
fun DropDownMenuTag(
    expanded: MutableState<Boolean>,
    taskTag: MutableState<Int>
){

    var expanded = expanded
    var taskTag = taskTag
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = true }
    ) {
        val items = listOf(
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(Color.Blue))
                },
                onClick = {
                    taskTag.value = 1
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.ColorLens,
                        contentDescription = "")
                }
            ),
            DropdownMenuItem(
                text = { Box(
                    Modifier
                        .width(100.dp)
                        .height(40.dp)
                        .background(Color.Green))},
                onClick = {
                    taskTag.value = 2
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.ColorLens,
                        contentDescription = "")
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(Color.Red))
                },
                onClick = {
                    taskTag.value = 3
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.ColorLens,
                        contentDescription = "")
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(colorResource(id = R.color.purple_200)))
                },
                onClick = {
                    taskTag.value = 4
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.ColorLens,
                        contentDescription = "")
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(colorResource(id = R.color.teal_200)))
                },
                onClick = {
                    taskTag.value = 5
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.ColorLens,
                        contentDescription = "")
                }
            )
        )
    }
}