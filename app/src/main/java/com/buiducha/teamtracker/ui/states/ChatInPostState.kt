package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.message.PostMessage

data class ChatInPostState(
    val messageList : MutableList<PostMessage> = mutableListOf()
)
