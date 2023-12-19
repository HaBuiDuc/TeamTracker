package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.ChatState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MessageViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _chatInPostState = MutableStateFlow(ChatState())
    val chatInPostState: StateFlow<ChatState> = _chatInPostState.asStateFlow()

    init {
        getMessage()
    }

    private fun getMessage() {
//        firebaseRepository.getMessage();
    }
}