package com.buiducha.teamtracker.data.model.project

import java.util.Date
import java.util.UUID

data class Note(
    val id: UUID = UUID.randomUUID(),
    val workspaceId: UUID,
    val content: String,
    val senderId: UUID,
    val time: Date
)
