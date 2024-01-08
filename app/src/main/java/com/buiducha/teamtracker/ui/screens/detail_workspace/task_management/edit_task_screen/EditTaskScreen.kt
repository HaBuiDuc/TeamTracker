package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.edit_task_screen

import android.view.textservice.SpellCheckerSession.SpellCheckerSessionParams
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.project.Task
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine
import com.buiducha.teamtracker.viewmodel.EditTaskViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

//Có 3 composable: CreateTaskScreen, TaskDateRangePicker và DropDownMenuTag
// TaskDateRangePicker: chọn ngày tháng bắt đầu và kết thúc
// DropDownMenuTag: Chọn tag

@Preview
@Composable
fun EditTaskPreview() {
    EditTaskScreen(navController = rememberNavController())
}

@Composable
fun EditTaskScreen(
    navController: NavController,
    editTaskViewModel: EditTaskViewModel = viewModel { EditTaskViewModel(Task()) }
) {
    val editTaskState by editTaskViewModel.editTaskState.collectAsState()

    //state quản lý mở/đóng các picker và dropdown menu
    val expanded = remember { mutableStateOf(false) }
    var isShowPicker by remember {
        mutableStateOf(false)
    }
    var dateSelected by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            EditTaskTopBar(
                onPopBack = {
                    navController.popBackStack()
                },
                onEditSubmit = {

                }
            )
        }
    ) { paddingValues ->
        if (isShowPicker) {
            TaskDatePicker(
                date = if (dateSelected) editTaskState.startDate else editTaskState.dueDate,
                onDismissRequest = {
                    isShowPicker = false
                },
                onConfirm = {time ->
                    if (!dateSelected) {
                        editTaskViewModel.setStartDate(time)
                    } else {
                        editTaskViewModel.setDueDate(time)
                    }
                },
            )
        }

        Column(
            Modifier
                .offset(y = 50.dp)
                .padding(paddingValues)
                .padding(10.dp)
        ) {
            Column {
                Text(
                    text = editTaskState.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
//                Row {
//                    Text(text = "Table A", fontWeight = FontWeight.Bold)
//                    Text(text = "  -  ")
//                    Text(text = "List B", fontWeight = FontWeight.Bold)
//                }
                HorizontalLine(
                    Modifier.padding(0.dp, 10.dp),
                    width = 1.2
                )
            }

// Nhập tên task
            TextField(
                value = editTaskState.title,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    editTaskViewModel.setTaskTitle(it)
                },
                label = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DriveFileRenameOutline,
                            contentDescription = ""
                        )
                        Text("Task Name")
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = editTaskState.description,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    editTaskViewModel.setTaskDes(it)
                },
                label = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.DriveFileRenameOutline,
                            contentDescription = ""
                        )
                        Text("Add task description")
                    }
                }
            )

//=======================================================================
// Chọn status (running, planning, done)
//            Spacer(modifier = Modifier.padding(5.dp))
//            Text(text = "Status")
//            Row(
//                Modifier
//                    .height(50.dp)
//                    .fillMaxWidth()
//                    .clickable { expanded.value = true }
//                    .background(color = Color.LightGray),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.More,
//                    contentDescription = "",
//                    modifier = Modifier.padding(15.dp)
//                )
//                BoxTagColor(taskTag = editTaskState.tag)
//                DropDownMenuTag(expanded = expanded, taskTag = taskTag)
//            }

//=======================================================================
// Thêm thành viên
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "Responsible member")
            Row(
                Modifier
                    .height(60.dp)
                    .background(color = Color.LightGray)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "",
                    modifier = Modifier.padding(15.dp)
                )
                //danh sách các thành viên tham gia nhiệm vụ
                //===============
                Row {

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

                IconButton(
                    onClick = { /*TODO*/ },
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "",
                        tint = Color.Blue
                    )
                }
            }

//=======================================================================
// Chọn thời gian bắt đầu, kết thúc

//            Spacer(modifier = Modifier.padding(5.dp))
//            Text(text = "Select start and end date")
//            Row(
//                Modifier
//                    .height(50.dp)
//                    .background(color = Color.LightGray)
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TaskDateRangePicker(
//                    startDate = (if (editTaskState.startDate != null) editTaskState.startDate else 0L)!!,
//                    endDate = (if (editTaskState.dueDate != null) editTaskState.dueDate else 0L)!!,
//                    setStartDate = {
//                        editTaskViewModel.setStartDate(it)
//                    },
//                    setDueDate = {
//                        editTaskViewModel.setDueDate(it)
//                    }
//                )
//            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
                    val startDate =
                        if (editTaskState.startDate != null) formatter.format(Date(editTaskState.startDate!!)) else "Start date ..."
                    Text(
                        text = startDate,
                        modifier = Modifier
                            .clickable {
                                dateSelected = false
                                isShowPicker = true
                            }
                            .fillMaxWidth()
                            .padding(
                                vertical = 8.dp
                            )
                    )
                    val dueDate =
                        if (editTaskState.dueDate != null) formatter.format(Date(editTaskState.dueDate!!)) else "Due date ..."
                    HorizontalLine()
                    Text(
                        text = dueDate,
                        modifier = Modifier
                            .clickable {
                                dateSelected = true
                                isShowPicker = true
                            }
                            .padding(
                                vertical = 8.dp
                            )
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDatePicker(
    date: Long?,
    onDismissRequest: () -> Unit,
    onConfirm: (Long) -> Unit
) {

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = date ?: System.currentTimeMillis()
    )
    DatePickerDialog(
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            onConfirm(datePickerState.selectedDateMillis!!)
        }
    ) {
        DatePicker(state = datePickerState)
    }
}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TaskDateRangePicker(
//    startDate: Long,
//    endDate: Long,
//    setStartDate: (Long) -> Unit,
//    setDueDate: (Long) -> Unit
//) {
//
//    val dateRangePickerState = rememberDateRangePickerState(
//        initialSelectedStartDateMillis = startDate,
//        initialSelectedEndDateMillis = endDate
//    )
//
//    var showDateRangePicker by remember {
//        mutableStateOf(false)
//    }
//
//    if (showDateRangePicker) {
//        DatePickerDialog(
//            onDismissRequest = {
//                showDateRangePicker = false
//            },
//            confirmButton = {
//                TextButton(onClick = {
//                    showDateRangePicker = false
//                    setStartDate(dateRangePickerState.selectedStartDateMillis!!)
//                    setDueDate(dateRangePickerState.selectedEndDateMillis!!)
//                }) {
//                    Text(text = "Confirm")
//                }
//            },
//            dismissButton = {
//                TextButton(onClick = {
//                    showDateRangePicker = false
//                }) {
//                    Text(text = "Cancel")
//                }
//            }
//        ) {
//            DateRangePicker(
//                state = dateRangePickerState,
//                modifier = Modifier.fillMaxSize(0.9f)
//            )
//        }
//    }
//
//    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ROOT)
//    TextButton(
//        onClick = { showDateRangePicker = true }
//    ) {
//        Text(
//            text = "${formatter.format(Date(startDate))} - ${formatter.format(Date(endDate))}"
//        )
//    }
//}

@Composable
fun DropDownMenuTag(
    expanded: MutableState<Boolean>,
    taskTag: MutableState<Int>
) {

//    var expanded = expanded
//    var taskTag = taskTag
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = true }
    ) {
        listOf(
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(Color.Blue)
                    )
                },
                onClick = {
                    taskTag.value = 1
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ColorLens,
                        contentDescription = ""
                    )
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(Color.Green)
                    )
                },
                onClick = {
                    taskTag.value = 2
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ColorLens,
                        contentDescription = ""
                    )
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(Color.Red)
                    )
                },
                onClick = {
                    taskTag.value = 3
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ColorLens,
                        contentDescription = ""
                    )
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(colorResource(id = R.color.purple_200))
                    )
                },
                onClick = {
                    taskTag.value = 4
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ColorLens,
                        contentDescription = ""
                    )
                }
            ),
            DropdownMenuItem(
                text = {
                    Box(
                        Modifier
                            .width(100.dp)
                            .height(40.dp)
                            .background(colorResource(id = R.color.teal_200))
                    )
                },
                onClick = {
                    taskTag.value = 5
                    expanded.value = false
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.ColorLens,
                        contentDescription = ""
                    )
                }
            )
        )
    }
}