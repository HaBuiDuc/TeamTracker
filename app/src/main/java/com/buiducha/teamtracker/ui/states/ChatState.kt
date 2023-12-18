package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.message.PostMessage

data class ChatState(
    val chatContent: String = "",
    val messageList : MutableList<PostMessage> = mutableListOf()
)
