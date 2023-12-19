package com.buiducha.teamtracker.data.model.project

import java.util.Date
import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val boardId: String = "",
    val title: String = "",
    val tag: String = "",
    val authorId: String = "",
    val startTime: Date = Date(),
    val dueTime: Date = Date(),
    val description: String = ""
)
