package com.buiducha.teamtracker.ui.screens.detail_project_screen.chat_in_post_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.message.PostMessage
import java.sql.Timestamp

@Preview
@Composable
fun MessageItemPreview() {
    MessageItem(
        message = PostMessage(
            id = "56789",
            postId = "456789",
            content = "Hello this is a message",
            userId = "3456789",
            time = 1234
        )
    )
}

@Composable
fun MessageItem(
    message: PostMessage
){
    Row(verticalAlignment = Alignment.Top,
        modifier = Modifier.padding(10.dp)) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.super_light_blue),
                    shape = CircleShape
                )
                .padding(12.dp)
                .size(20.dp)
        ) {
            Text(
//                text = workspace.name.substring(0, 2).uppercase(Locale.ROOT),
                text = "CO",
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Row {
                Text(
                    text = message.userId.substring(0, 10),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = message.time.toString().substring(0, 19),
                    fontSize = 12.sp, color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                )
            }

            Text(
                text = message.content
            )
        }
    }
}