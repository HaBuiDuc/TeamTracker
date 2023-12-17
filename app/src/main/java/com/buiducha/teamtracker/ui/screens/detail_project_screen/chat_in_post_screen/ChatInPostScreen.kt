package com.buiducha.teamtracker.ui.screens.detail_project_screen.chat_in_post_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.data.model.message.PostMessage
import java.sql.Timestamp
import java.util.UUID

@Preview(showSystemUi = true)
@Composable
fun ChatInPostPrev(){
    ChatInPostScreen(navController = rememberNavController())
}

@Composable
fun ChatInPostScreen(
    navController: NavController
){
    var mesContent by remember { mutableStateOf("") }

    val postId = UUID.randomUUID().mostSignificantBits.toString()
    val userId = UUID.randomUUID().mostSignificantBits.toString()
    val time = 1234452345345
    val content = "Nội dung tin nhắn"

    val message = PostMessage(
        postId = postId,
        content = content,
        userId = userId,
        time = time
    )

    Column{
        Column(modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .verticalScroll(rememberScrollState())) {
            MessageItem(message)
            MessageItem(message)
            MessageItem(message)
            MessageItem(message)
            MessageItem(message)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 5.dp)
        ) {
            TextField(
                value = mesContent,
                onValueChange = { mesContent = it },
                singleLine = false,
                maxLines = 3,
                modifier = Modifier.weight(1f)
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .width(70.dp)
                    .align(Alignment.CenterVertically)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent
                )
            ) {
                Icon(imageVector = Icons.Filled.Send,
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }
    }
}