package com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen.demo_data

data class MessageData(
    val id: Int,
    val content: String,
    val timestamp: Long,
    val sender: String,
    val receiver: String
)

val messages = listOf(
    MessageData(1, "Hello, how are you?", System.currentTimeMillis(), "User1", "User2"),
    MessageData(2, "I'm fine, thank you!", System.currentTimeMillis(), "User2", "User1"),
    MessageData(3, "What are you doing?", System.currentTimeMillis(), "User1", "User2"),
    MessageData(4, "I'm working.", System.currentTimeMillis(), "User2", "User1"),
    MessageData(5, "Do you free time tonight?", System.currentTimeMillis(), "User1", "User2"),
    MessageData(6, "Yes, I do. Why?\n hallo bro ncsjn cnsjnc fnaj nnc nsacn nsjsn sja ncj anc ndn ajd", System.currentTimeMillis(), "User2", "User1"),
    MessageData(7, "Hello, how are you?", System.currentTimeMillis(), "User1", "User2"),
    MessageData(8, "I'm fine, thank you!", System.currentTimeMillis(), "User2", "User1"),
    MessageData(9, "What are you doing?", System.currentTimeMillis(), "User1", "User2"),
    MessageData(10, "I'm working.", System.currentTimeMillis(), "User2", "User1"),
)
