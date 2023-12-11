package com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen.demo_data.messages

@Preview
@Composable
fun MessageItem() {
    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            messages.forEach { message ->
                // change: check if senderId match with current user id
                if (message.sender == "User1") {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, start = 40.dp)
                    ) {
                        BoxWithConstraints(
                            modifier = Modifier.weight(1f),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Card (shape = RoundedCornerShape(8.dp)){
                                Box(modifier = Modifier
                                    .background(Color(red = 91, green = 96, blue = 200))
                                    .padding(8.dp),
                                ){
                                    Text(
                                        text = message.content,
                                        color = Color.White
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(5.dp)
                                .padding(end = 0.dp)
                                .background(Color.Green, CircleShape)
                        ){
                            Image(
                                // Change image with image uri
                                painter = painterResource(id = R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier.clip(CircleShape)
                            )
                        }
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 2.dp, bottom = 2.dp, end = 40.dp)) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(5.dp)
                                .padding(end = 0.dp)
                                .background(Color.Green, CircleShape)
                        ){
                            Image(
                                // Change image with image uri
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = null,
                                modifier = Modifier.clip(CircleShape)
                            )
                        }
                        Card (shape = RoundedCornerShape(8.dp)){
                            Box(modifier = Modifier
                                .background(Color(red = 245, green = 245, blue = 245))
                                .padding(8.dp),
                            ){
                                Text(
                                    text = message.content,
                                    color = Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}