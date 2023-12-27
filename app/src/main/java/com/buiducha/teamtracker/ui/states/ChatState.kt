package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.message.PostMessage
import com.buiducha.teamtracker.data.model.user.UserData

data class ChatState(
    val chatContent: String = "",
    val messageList : MutableList<PostMessage> = mutableListOf(),
    val userList: List<UserData> = emptyList()
)
