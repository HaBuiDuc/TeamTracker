package com.buiducha.teamtracker.ui.screens.notification_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.notification_screen.demo_data.Notification
import com.buiducha.teamtracker.ui.screens.notification_screen.demo_data.notificationList

@Preview
@Composable
fun NotificationPreview() {
    NotificationTab()
}
@Composable
fun NotificationTab() {

    Column {
        SearchBar()
        val checkedState = remember { mutableStateOf(false) }
        Row() {
            Switch(
                modifier = Modifier
                    .padding(start = 15.dp),
                checked = checkedState.value,
                onCheckedChange = { isChecked -> checkedState.value = isChecked }
            )
            Text(
                text = "Chỉ chưa đọc",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .fillMaxWidth()
                    .align(CenterVertically)
            )
        }
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()
        ) {
            notificationList.forEach { notification ->
                if (checkedState.value) {
                    if (notification.isRead) {
                        notificationItem(notification)
                    }
                } else {
                    notificationItem(notification)
                }
            }
        }
    }
}
