package com.buiducha.teamtracker.ui.screens.detail_workspace.post_chat_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.viewmodel.PostChatViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel

@Preview(showSystemUi = true)
@Composable
fun ChatInPostPrev() {
//    ChatScreen(navController = rememberNavController())
}

@Composable
fun PostChatScreen(
    navController: NavController,
    selectedPostViewModel: SelectedPostViewModel,
    chatViewModel: PostChatViewModel = viewModel {
        PostChatViewModel(selectedPostViewModel)
    },
) {
    val chatState by chatViewModel.chatState.collectAsState()
    val scrollState = rememberLazyListState()

    Scaffold(
        topBar = {
            ChatTopBar(
                onPopBack = {
                    navController.popBackStack()
                }
            )
        },
        bottomBar = {
            ChatInput(
                value = chatState.chatContent,
                onValueChange = {
                    chatViewModel.setChatContent(it)
                },
                onMessageSend = {
                    chatViewModel.sendMessage()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn(
                state = scrollState
            ) {
                items(chatState.messageList) { message ->
                    MessageItem(
                        message = message,
                        user = chatState.userList.find { user -> user.id == message.userId } ?: UserData()
                    )
                }
            }
        }
    }
    if (chatState.messageList.isNotEmpty()) {
        LaunchedEffect(Unit) {
            scrollState.scrollToItem(index = chatState.messageList.size - 1)
        }
    }

}