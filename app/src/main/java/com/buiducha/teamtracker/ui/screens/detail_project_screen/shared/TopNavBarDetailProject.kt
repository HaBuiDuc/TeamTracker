package com.buiducha.teamtracker.ui.screens.detail_project_screen.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.navigation.Screen


@Composable
fun TopNavBarDetailProject(navController: NavController, tabIndex: Int) {
    val tabs = listOf("Nhắn tin", "Công việc", "Thành viên")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            selectedTabIndex = tabIndex,
            indicator = { tabPositions ->
                val selectedTabPosition = remember { mutableStateOf(tabPositions[tabIndex]) }
                LaunchedEffect(tabIndex) {
                    selectedTabPosition.value = tabPositions[tabIndex]
                }
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(selectedTabPosition.value)
                        .width(selectedTabPosition.value.width)
                        .height(1.dp)
                        .background(Color.Red)
                )
            }
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = {
                        when (index) {
                            0 -> navController.navigate(Screen.MessageScreen.route)
                            1 -> navController.navigate(Screen.TaskManagerScreen.route)
                            2 -> navController.navigate(Screen.TeamMemberDetailScreen.route)
                        }
                    }
                )
            }
        }
    }
}