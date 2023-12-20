package com.buiducha.teamtracker.data.model.project

import java.util.UUID

data class Board(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val workspaceId: String = "",
    val label: String = "",
    val taskList: List<Task> = emptyList()
)
