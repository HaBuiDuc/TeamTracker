package com.buiducha.teamtracker.data.model.project

import java.util.Date
import java.util.UUID

data class Workspace(
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val describe: String? = null,
    val startDay: Date,
)
