package com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R

@Preview()
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInputAndSend(){
    val messageState = remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape = CircleShape)
            )
            Spacer(modifier = Modifier.width(5.dp))
            Row(modifier = Modifier
                .weight(1f)) {
                Card(
                    modifier = Modifier
                        .weight(1f)
                        .clip(
                            shape = RoundedCornerShape(30.dp)
                        )
                        .border(1.dp, Color.Black, shape = RoundedCornerShape(30.dp))
                ) {
                    TextField(
                        value = messageState.value,
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 0.dp, vertical = 0.dp)
                            .height(50.dp),
                        onValueChange = { messageState.value = it },
                    )
                }
            }
            Spacer(modifier = Modifier.width(5.dp))
            Button(
                modifier = Modifier
                    .background(Color.Transparent)
                    .height(50.dp)
                    .width(80.dp),
                onClick = { /* Handle send button click */ },
            ) {
                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = ""
                )
            }
        }
    }
}