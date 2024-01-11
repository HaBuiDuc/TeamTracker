package com.buiducha.teamtracker.data.model

import java.util.UUID


data class Notification (
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val receiverId: String = "",
    val sender: String = "",
    val content: String = "",
    val time: String = "",
    val isRead: Boolean = false
)
