package com.buiducha.teamtracker.ui.screens.notification_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R

import androidx.compose.ui.Alignment.Companion.CenterVertically
import com.buiducha.teamtracker.data.model.Notification

@Composable
fun notificationItem(notification: Notification?){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(48.dp)
                .clip(shape = CircleShape),
            contentScale = ContentScale.Crop,
        )
        Column(modifier = Modifier
            .padding(start = 16.dp)
            .align(CenterVertically)) {
            Row {
                if (notification != null) {
                    Text(text = notification.content)
                }
            }




            if (notification != null) {
                Text(text = notification.time, fontSize = 12.sp)
            }
        }
    }
}
