package com.buiducha.teamtracker.ui.screens.notification_screen.demo_data

data class User(val username: String, val imageUrl: String)


val users = listOf(
    User(
        "user1",
        "url"
    ),
    User(
        "user2",
        "url"
    ),
    User(
        "user3",
        "url"
    ),
)

data class Notification(
    val idUser: Int,
    val username: String,
    val content: String,
    val time: String,
    val isRead: Boolean
)


val notificationList = listOf(
    Notification(1, "user1", "thêm vào workspace X", "10:00 AM", true),
    Notification(2, "user2", "giao nhiệm vụ A", "11:30 AM", true),
    Notification(3, "user3", "giao nhiệm vụ A", "2:45 PM", false),
    Notification(4, "user1", "giao nhiệm vụ A", "3:00 PM", false),
    Notification(5, "user2", "thêm vào workspace X", "4:30 PM", true),
    Notification(6, "user3", "thêm vào workspace X", "5:45 PM", false),
    Notification(7, "user1", "giao nhiệm vụ A", "6:15 PM", true)
)
