package com.buiducha.teamtracker.ui.screens.notification_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.Notification
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine

@Composable
fun notificationItem(notification: Notification?){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_green))
            .height(80.dp)
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        Image(
            painter = painterResource(id = R.drawable.teamtracker2),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape)
                .align(CenterVertically),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier
            .padding(10.dp)
            .align(CenterVertically)) {
            Row {
                if (notification != null) {
                    Text(text = notification.content,
                        fontWeight = FontWeight.Medium)
                }
            }
            if (notification != null) {
                Text(text = notification.time, fontSize = 12.sp)
            }
        }
    }
    HorizontalLine()
}
