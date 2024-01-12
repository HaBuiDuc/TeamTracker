package com.buiducha.teamtracker.ui.states

data class CreateNotificationState(
    val receiverId: String = "",
    val sender: String = "",
    val content: String = "",
    val time: String = ""
)
