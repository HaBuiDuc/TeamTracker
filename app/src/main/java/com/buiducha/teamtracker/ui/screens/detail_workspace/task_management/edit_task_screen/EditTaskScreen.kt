package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.edit_task_screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DriveFileRenameOutline
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen.AddMemberToTaskDialog
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine
import com.buiducha.teamtracker.viewmodel.CreateNotificationViewModel
import com.buiducha.teamtracker.viewmodel.EditTaskViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Preview
@Composable
fun EditTaskPreview() {
//    EditTaskScreen(navController = rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditTaskScreen(
    navController: NavController,
    taskId: String,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    editTaskViewModel: EditTaskViewModel = viewModel {
        EditTaskViewModel(
            taskId = taskId,
            selectedWorkspace = selectedWorkspaceViewModel
        )
    },
    createNotificationViewModel: CreateNotificationViewModel
) {
    val editTaskState by editTaskViewModel.editTaskState.collectAsState()

    var isShowPicker by remember {
        mutableStateOf(false)
    }
    var dateSelected by remember {
        mutableStateOf(false)
    }
    var isShowMemberDialog by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            EditTaskTopBar(
                onPopBack = {
                    navController.popBackStack()
                },
                onEditSubmit = {
                    editTaskViewModel.updateTask(
                        onUpdateSuccess = {
                            navController.popBackStack()
                        },
                        onUpdateFailure = {

                        }
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isShowMemberDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Default.PersonAdd,
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        if (isShowMemberDialog) {
            AddMemberToTaskDialog(
                memberList = editTaskState.remainMemberList,
                onDismissRequest = {
                    isShowMemberDialog = false
                },
                selectedMember = editTaskState.selectedUser,
                onConfirm = {
                    editTaskViewModel.onAddMember()
                    editTaskViewModel.editTaskState
                        .value.selectedUser.forEach{ userId ->
                            createNotificationViewModel.setContent("You have been added to task " + editTaskState.title)
                            createNotificationViewModel.setReceiverId(userId)
                            createNotificationViewModel.setTime()
                            createNotificationViewModel.createNotification()
                    }
                },
                onCheckChange = {
                    editTaskViewModel.onSelectMember(it)
                }
            )
        }

        if (isShowPicker) {
            TaskDatePicker(
                date = if (dateSelected) editTaskState.startDate else editTaskState.dueDate,
                onDismissRequest = {
                    isShowPicker = false
                },
                onConfirm = { time ->
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
            Column {
                var isExpand by remember {
                    mutableStateOf(false)
                }
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            isExpand = !isExpand
                        }
                ) {
                    Text(
                        text = "Responsible member",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        imageVector = if (isExpand) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                if (isExpand) {
                    LazyColumn {
                        items(editTaskState.joinedMemberList) { member ->
                            MemberView(member = member)
                        }
                    }
                }
            }

//            Row(
//                Modifier
//                    .height(60.dp)
//                    .background(color = Color.LightGray)
//                    .fillMaxWidth(),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Person,
//                    contentDescription = "",
//                    modifier = Modifier.padding(15.dp)
//                )
//                //danh sách các thành viên tham gia nhiệm vụ
//                //===============
//                Row {
//
//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier
//                            .background(
//                                color = colorResource(id = R.color.super_light_blue),
//                                shape = CircleShape
//                            )
//                            .padding(12.dp)
//                            .size(28.dp)
//                    ) {
//                        Text(
//                            text = "CN",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
//                }
//                //==================
//
//                IconButton(
//                    onClick = { /*TODO*/ },
//                ) {
//                    Icon(
//                        imageVector = Icons.Filled.Add,
//                        contentDescription = "",
//                        tint = Color.Blue
//                    )
//                }
//            }

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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MemberView(
    member: UserData
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (member.avatarUri != null) {
            GlideImage(
                model = member.avatarUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10))
                    .width(44.dp)
                    .aspectRatio(1f)
            )
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = Color.Blue,
                        shape = CircleShape
                    )
                    .padding(10.dp)
                    .size(24.dp)
            ) {
                Text(text = member.fullName.substring(0, 2).uppercase(Locale.ROOT))
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = member.fullName,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
    }
}