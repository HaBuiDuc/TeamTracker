package com.buiducha.teamtracker.ui.screens.detail_project_screen.chat_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.viewmodel.ChatViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedPostViewModel

@Preview(showSystemUi = true)
@Composable
fun ChatInPostPrev() {
//    ChatScreen(navController = rememberNavController())
}

@Composable
fun ChatScreen(
    navController: NavController,
    selectedPostViewModel: SelectedPostViewModel,
    chatViewModel: ChatViewModel = viewModel {
        ChatViewModel(selectedPostViewModel)
    },
) {
    val chatState by chatViewModel.chatState.collectAsState()

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
//            Column(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .weight(1f)
//                    .verticalScroll(rememberScrollState())
//            ) {
//                MessageItem(message)
//                MessageItem(message)
//                MessageItem(message)
//                MessageItem(message)
//                MessageItem(message)
//            }

            LazyColumn {
                items(chatState.messageList) { message ->
                    MessageItem(message = message)
                }
            }
        }
    }


}