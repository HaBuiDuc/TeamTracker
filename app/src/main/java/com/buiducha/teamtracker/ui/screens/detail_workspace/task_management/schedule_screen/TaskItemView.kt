package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Message
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.project.Task
import com.buiducha.teamtracker.ui.screens.detail_workspace.task_management._share.BoxTagColor

@Composable
fun TaskItemView(
    task: Task
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        modifier = Modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(8.dp)
            )

    ) {
        Column(Modifier.padding(10.dp)) {
            BoxTagColor(
                taskTag = 2
            )
            Text(text = task.title)
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
                if (task.startTime != null && task.dueTime != null) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Filled.AccessTime,
                            contentDescription = null
                        )
                        Text(
                            text = "18/12/2023 - 18/12/2023"
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Icon(
                    imageVector = Icons.Filled.Message,
                    contentDescription = null
                )
                Text(
                    text = "11"
                )
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.super_light_blue),
                            shape = CircleShape
                        )
                        .padding(12.dp)
                        .size(22.dp)
                ) {
                    Text(
                        text = "CN",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}