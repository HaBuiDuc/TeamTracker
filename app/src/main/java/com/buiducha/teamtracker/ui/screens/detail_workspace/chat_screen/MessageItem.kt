package com.buiducha.teamtracker.ui.screens.detail_workspace.chat_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.message.PostMessage
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.ui.theme.DarkGreen
import com.buiducha.teamtracker.utils.convertDate
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Preview
@Composable
fun MessageItemPreview() {
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MessageItem(
    message: PostMessage,
    user: UserData
){
    Row(verticalAlignment = Alignment.Top,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        if(user.avatarUri == null){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(
                        color = DarkGreen,
                        shape = CircleShape
                    )
                    .padding(10.dp)
            ) {
                Text(
                    text = if(user.fullName.length > 3) user.fullName.substring(0, 2).uppercase() else "",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
        else{
            GlideImage(
                model = user.avatarUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(45.dp)
                    .aspectRatio(1f)
            )
        }

        Column(modifier = Modifier.padding(start = 8.dp)) {
            Row {
                Text(
                    text = user.fullName,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = message.time?.let { convertDate(it) } ?: "",
                    fontSize = 12.sp, color = Color.Gray,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 5.dp),
                )
            }

            Text(
                text = message.content
            )
        }
    }
}