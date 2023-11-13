package com.buiducha.teamtracker.ui.screens.detail_project_screen

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen.MessageTab
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.TaskManagerTab
import com.buiducha.teamtracker.ui.screens.detail_project_screen.team_member_detail.TeamMemberDetail

@Preview(showSystemUi = true)
@Composable
fun TabBar(){
    val tabs = listOf("Nhắn tin", "Công việc", "Thành viên")
    var tabIndex by rememberSaveable { mutableStateOf(1) }
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
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> MessageTab()
            1 -> TaskManagerTab()
            2 -> TeamMemberDetail()
        }
    }
}