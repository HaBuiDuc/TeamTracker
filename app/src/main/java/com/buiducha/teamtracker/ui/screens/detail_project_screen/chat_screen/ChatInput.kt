package com.buiducha.teamtracker.ui.screens.detail_project_screen.chat_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatInput(
    value: String,
    onValueChange: (String) -> Unit,
    onMessageSend: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            textStyle = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.DarkGray
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp,
                            color = Color.Gray,
                            shape = RoundedCornerShape(size = 12.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 10.dp), // inner padding
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = "Reply",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.LightGray
                        )
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .weight(1f)
        )

        IconButton(
            onClick = {
                onMessageSend()
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Send,
                contentDescription = "",
                tint = Color.Black
            )
        }
    }
}