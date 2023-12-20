package com.buiducha.teamtracker.data.model.project

import java.util.Date
import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val boardId: String = "",
    val title: String = "",
    val tag: String = "",
    val authorId: String = "",
    val startTime: Date? = null,
    val dueTime: Date? = null,
    val description: String = ""
)
