package com.buiducha.teamtracker.data.model.project

import java.util.UUID

data class Task(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val boardId: String = "",
    val title: String = "",
    val tag: Int = 0,
    val authorId: String = "",
    val startDate: Long? = null,
    val dueDate: Long? = null,
    val description: String = ""
)
