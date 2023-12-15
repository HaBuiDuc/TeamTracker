package com.buiducha.teamtracker.data.model.project

import java.sql.Timestamp
import java.util.UUID

data class Posts(
    val id: UUID = UUID.randomUUID(),
    val workspaceId: UUID,
    val userId: UUID,
    val content: String,
    val timestamp: Timestamp,
    val likesCount: Int
)
