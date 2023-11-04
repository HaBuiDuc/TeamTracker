package com.buiducha.teamtracker.data.model.message

import java.util.Date
import java.util.UUID

data class Message(
    val id: UUID = UUID.randomUUID(),
    val workspaceId: UUID,
    val content: String,
    val senderId: UUID,
    val time: Date
)
