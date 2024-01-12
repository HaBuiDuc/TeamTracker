package com.buiducha.teamtracker.viewmodel.post_viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.buiducha.teamtracker.data.model.message.PostMessage
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.ChatState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class PostChatViewModel(
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

        setChatContent("")
    }

    private fun getMessages() {
        firebaseRepository.getMessages(
            postId = selectedPostViewModel.post.value.id,
            onGetMessagesSuccess = {messages ->


                viewModelScope.launch {
                    val userList = mutableListOf<UserData>()
                    messages.forEach { message ->
                        val user = suspendCoroutine { continuation ->
                            firebaseRepository.getUserInfo(
                                userId = message.userId,
                                onGetInfoSuccess = { user ->
                                    continuation.resume(user)
                                },
                                onGetInfoFailure = {}
                            )
                        }
                        userList += user
                    }
                    _chatState.value = _chatState.value.copy(
                        messageList = messages,
                        userList = userList
                    )

                }
            }
        )
    }
}