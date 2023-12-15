package com.buiducha.teamtracker.data.model.message

import java.sql.Timestamp
import java.util.Date
import java.util.UUID

data class Message(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val postId: UUID,
    val content: String,
    val userId: UUID,
    val time: Timestamp
)
