package com.buiducha.teamtracker.ui.screens.detail_workspace.chat_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.utils.convertDate

@Preview
@Composable
fun MessageItemPreview() {
}

@Composable
fun MessageItem(
    message: PostMessage,
    user: UserData
){
    Row(verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
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
                text = if (user.fullName.length > 3) user.fullName.substring(0, 2).uppercase() else user.fullName.uppercase(),
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Row {
                Text(
                    text = user.fullName,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = message.time?.let { convertDate(it) } ?: "",
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