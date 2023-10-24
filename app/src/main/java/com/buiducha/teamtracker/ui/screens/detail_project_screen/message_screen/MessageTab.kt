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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MessageTab(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        contentAlignment = Alignment.TopCenter) {
        Column {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(0.dp)
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                MessageItem()
            }
            Box(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                MessageInputAndSend()
            }
        }
    }
}