package com.buiducha.teamtracker.data.model.project

import java.util.UUID

data class Board(
    val id: UUID = UUID.randomUUID(),
    val workspaceId: String = "",
    val label: String = "",
    val taskList: List<Task> = emptyList()
)
