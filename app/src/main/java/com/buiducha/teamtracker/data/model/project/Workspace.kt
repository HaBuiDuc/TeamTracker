package com.buiducha.teamtracker.data.model.project

import java.util.UUID

data class Workspace(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val name: String = "",
    val describe: String? = null,
    val startDay: String = "",
    val avatar: String? = null
)
