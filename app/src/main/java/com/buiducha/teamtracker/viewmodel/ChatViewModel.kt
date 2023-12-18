package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.message.PostMessage
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.ChatState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ChatViewModel(
    private val selectedPostViewModel: SelectedPostViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _chatState = MutableStateFlow(ChatState())
    val chatState: StateFlow<ChatState> = _chatState.asStateFlow()

    init {
        getMessages()
    }

    fun setChatContent(chatContent: String) {
        _chatState.value = _chatState.value.copy(
            chatContent = chatContent
        )
    }

    fun sendMessage() {
        if (chatState.value.chatContent.isEmpty()) {
            return
        }
        val message = PostMessage(
            postId = selectedPostViewModel.post.value.id,
            content = chatState.value.chatContent,
            userId = firebaseRepository.getCurrentUser()?.uid!!,
        )

        firebaseRepository.sendMessage(
            message = message
        )
    }

    private fun getMessages() {
        firebaseRepository.getMessages(
            postId = selectedPostViewModel.post.value.id,
            onGetMessagesSuccess = {messages ->
                _chatState.value = _chatState.value.copy(
                    messageList = messages
                )
            }
        )
    }
}