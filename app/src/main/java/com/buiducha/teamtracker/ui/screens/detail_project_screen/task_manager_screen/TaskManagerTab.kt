package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskData
import com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data.taskHeader

@Composable
fun TaskManagerTab(){
    SimpleTable(taskHeader = taskHeader, rows = taskData) { newItem ->
        taskData.add(newItem)
    }
}