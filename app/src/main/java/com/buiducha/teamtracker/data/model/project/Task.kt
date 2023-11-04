package com.buiducha.teamtracker.data.model.project

import java.util.Date
import java.util.UUID

data class Task(
    val id: UUID = UUID.randomUUID(),
    val taskTableId: UUID,
    val name: String,
    val ownerId: UUID,
    val startTime: Date,
    val finishTime: Date,
    val status: String
)
