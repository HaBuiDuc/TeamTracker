package com.buiducha.teamtracker.ui.screens.notification_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.buiducha.teamtracker.data.model.Notification
import com.buiducha.teamtracker.viewmodel.NotificationViewModel




@Composable
fun NotificationScreen(
    notificationViewModel: NotificationViewModel,
    navController: NavController
) {
    val notificationState by notificationViewModel.notificationState.collectAsState()

    val searchText = remember { mutableStateOf("") }
    val listNotification: List<Notification> = notificationState.notificationList.filter {
        it.content.contains(searchText.value)
    }

    Column {
        SearchBar(navController = navController, searchText = searchText)
        Column(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxSize()
        ) {
            LazyColumn(state = rememberLazyListState()){
                items(listNotification)
                { notification ->
                    notificationItem(notification)
                }
            }

        }
    }
}