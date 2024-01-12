package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.Notification

data class NotificationState(
    val notificationList: List<Notification> = emptyList()
)
