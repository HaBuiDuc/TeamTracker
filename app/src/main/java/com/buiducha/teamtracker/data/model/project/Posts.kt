package com.buiducha.teamtracker.data.model.project

import java.sql.Timestamp
import java.util.UUID

data class Posts(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val workspaceId: String? = null,
    val userId: String? = null,
    val content: String = "new post",
    val timestamp: Long? = System.currentTimeMillis(),
    val likesCount: Int = 0
)
