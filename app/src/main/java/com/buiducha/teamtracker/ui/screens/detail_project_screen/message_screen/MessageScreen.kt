package com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.screens.detail_project_screen.shared.TopNavBarDetailProject
import com.buiducha.teamtracker.viewmodel.MessageViewModel

@Composable
fun MessageScreen(
    navController: NavController,
    messageViewModel: MessageViewModel = viewModel()
){
    Box(modifier = Modifier
        .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter) {
        Column {
            TopNavBarDetailProject(navController = navController, tabIndex = 0)
            NoteArea()
            Box(
                modifier = Modifier
                    .height(0.dp)
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                MessageItem()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MessageInputAndSend()
            }
        }
    }
}