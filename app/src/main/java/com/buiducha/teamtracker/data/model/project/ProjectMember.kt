package com.buiducha.teamtracker.data.model.project

import java.util.UUID

data class ProjectMember(
    val userId: UUID = UUID.randomUUID(),
    val workspaceId: UUID
)
