package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.message.Message

data class ChatInPostState(
    val MessageList : MutableList<Message> = mutableListOf()
)
