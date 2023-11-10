package com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BookmarkRemove
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.detail_project_screen.message_screen.demo_data.messages

@Preview(showSystemUi = true)
@Composable
fun NoteArea(){
    Box(modifier = Modifier
        .height(150.dp)
        .clip(shape = RoundedCornerShape(20.dp))
        .padding(2.dp)
        ) {
        Column(modifier = Modifier
            .height(150.dp)
            .background(color = colorResource(id = R.color.blue_2))
            .padding(10.dp)) {
            Row {
                Icon(imageVector = Icons.Filled.PushPin,
                    contentDescription = "",
                    tint = Color.White
                )
                Text(text = "ĐÃ GHIM",
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
            Column(modifier = Modifier
                .verticalScroll(rememberScrollState())){
                messages.forEach{messageData ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(12.dp))
                        Icon(imageVector = Icons.Filled.Add, contentDescription = "",
                            tint = Color.White)
                        Text(text = "Lưu ý: " + messageData.content,
                            color = Color.White,
                            modifier = Modifier.weight(1f))
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Filled.BookmarkRemove,
                                contentDescription = "",
                                tint = Color.White)
                        }
                    }
                }
            }

        }
    }


}