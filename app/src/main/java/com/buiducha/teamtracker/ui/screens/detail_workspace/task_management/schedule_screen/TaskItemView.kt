package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.RemoveRedEye
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.project.Task
import com.buiducha.teamtracker.ui.screens.detail_workspace.task_management._share.BoxTagColor
import java.text.SimpleDateFormat
import java.util.Date

@Preview
@Composable
fun TaskItemPreview() {
    TaskItemView(
        task = Task(
            title = "This is a task"
        )
    ) {}
}

@SuppressLint("SimpleDateFormat")
@Composable
fun TaskItemView(
    task: Task,
    onTaskPressed: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .clickable {
                onTaskPressed()
            }

    ) {
        Column(Modifier.padding(10.dp)) {
            if (task.tag != 0) {
                BoxTagColor(
                    taskTag = task.tag
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = task.title,
                fontSize = 18.sp,
            )

            if (task.startDate != null || task.dueDate != null) {
                Spacer(modifier = Modifier.padding(5.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.RemoveRedEye,
                        contentDescription = null
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        if (task.startDate != null && task.dueDate != null) {
                            val dateFormat = SimpleDateFormat("dd/MM/yyyy")
                            val startDate = Date(task.startDate)
                            val dueDate = Date(task.dueDate)
                            Icon(
                                imageVector = Icons.Filled.AccessTime,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "${dateFormat.format(startDate)} - ${dateFormat.format(dueDate)}"
                            )
                        } else if (task.startDate != null) {
                            val dateFormat = SimpleDateFormat("MMM dd, yyyy")
                            val startDate = Date(task.startDate)
                            Icon(
                                imageVector = Icons.Filled.AccessTime,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Started: ${dateFormat.format(startDate)}")
                        } else if (task.dueDate != null) {
                            val dateFormat = SimpleDateFormat("MMM dd, yyyy")
                            val dueDate = Date(task.dueDate)
                            Icon(
                                imageVector = Icons.Filled.AccessTime,
                                contentDescription = null
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "Finished: ${dateFormat.format(dueDate)}")
                        }
                    }
                }
//                Spacer(modifier = Modifier.padding(5.dp))
//                Row(
//                    horizontalArrangement = Arrangement.End,
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Box(
//                        contentAlignment = Alignment.Center,
//                        modifier = Modifier
//                            .background(
//                                color = colorResource(id = R.color.super_light_blue),
//                                shape = CircleShape
//                            )
//                            .padding(12.dp)
//                            .size(22.dp)
//                    ) {
//                        Text(
//                            text = "CN",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.SemiBold
//                        )
//                    }
//                }
            }

//            Row {
//                Icon(
//                    imageVector = Icons.Filled.Message,
//                    contentDescription = null
//                )
//                Text(
//                    text = "11"
//                )
//            }

        }
    }
}