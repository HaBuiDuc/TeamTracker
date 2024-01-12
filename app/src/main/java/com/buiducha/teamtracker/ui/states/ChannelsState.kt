package com.buiducha.teamtracker.ui.states

import io.getstream.chat.android.models.Channel

data class ChannelsState(
    val channelList: List<Channel> = emptyList()
)
