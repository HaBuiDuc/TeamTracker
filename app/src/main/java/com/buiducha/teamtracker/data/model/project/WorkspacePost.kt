package com.buiducha.teamtracker.data.model.project

import java.util.UUID

data class WorkspacePost(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val workspaceId: String? = null,
    val userId: String? = null,
    val title: String = "",
    val content: String = "",
    val timestamp: Long? = System.currentTimeMillis(),
    val likesCount: Int = 0
)
