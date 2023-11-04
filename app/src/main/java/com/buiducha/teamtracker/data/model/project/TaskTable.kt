package com.buiducha.teamtracker.data.model.project

import java.util.Date
import java.util.UUID

data class TaskTable(
    val id: UUID = UUID.randomUUID(),
    val workspaceId: UUID,
    val name: String,
    val createTime: Date
)
