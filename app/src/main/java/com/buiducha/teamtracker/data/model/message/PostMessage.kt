package com.buiducha.teamtracker.data.model.message

import java.sql.Timestamp
import java.util.UUID

data class PostMessage(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val postId: String,
    val content: String,
    val userId: String,
    val time: Long? = System.currentTimeMillis()
)
