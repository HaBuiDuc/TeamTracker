package com.buiducha.teamtracker.ui.screens.detail_project_screen.task_manager_screen.demo_data

data class TaskData(val s: String, val s1: String, val s2: String, val s3: String, val s4: String)

var taskData = mutableListOf(
    TaskData("Task 1", "Owner 1", "2022-01-01", "2022-01-10", "Running"),
    TaskData("Task 2", "Owner 2", "2022-02-01", "2022-02-10", "Done"),
    TaskData("Task 3", "Owner 3", "2022-03-01", "2022-03-10", "Planning"),
    TaskData("Task 4", "Owner 4", "2022-04-01", "2022-04-10", "Running"),
    TaskData("Task 5", "Owner 5", "2022-05-01", "2022-05-10", "Done"),
)

val taskHeader = TaskData("Task", "Owner", "Start", "Deadline", "Status")