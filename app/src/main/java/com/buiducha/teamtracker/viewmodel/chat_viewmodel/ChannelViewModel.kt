package com.buiducha.teamtracker.viewmodel.chat_viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.StreamRepository

class ChannelViewModel : ViewModel() {
    private val streamRepository = StreamRepository.get()

    fun clientInitState() =  streamRepository.client.clientState.initializationState

}