package com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.utils.convertDate
import com.buiducha.teamtracker.viewmodel.PostViewModel

@Composable
fun PostItem(
    post: WorkspacePost,
    onViewMessage: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                8.dp
            )
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onViewMessage()
            },
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        )
    )
    {
        Column(
            modifier = Modifier.padding(
                10.dp,
                10.dp,
                0.dp,
                10.dp
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = colorResource(id = R.color.super_light_blue),
                            shape = CircleShape
                        )
                        .padding(12.dp)
                        .size(28.dp)
                ) {
                    Text(
                        text = post.title.substring(0, 2).uppercase(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column {
                    Text(
                        modifier = Modifier
                            .height(29.dp)
                            .padding(start = 5.dp),
                        text = post.userId.toString(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                    Text(
                        modifier = Modifier
                            .height(29.dp)
                            .padding(5.dp),
                        fontStyle = FontStyle.Italic,
                        text = convertDate(post.timestamp!!)
                    )
                }
                IconButton(
                    onClick = {

                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                        Modifier.clickable {

                        }
                    )
                }

            }

            Column {
                Text(
                    text = post.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = post.content,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Row {
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUpOffAlt,
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    fontStyle = FontStyle.Italic,
                    text = "100 likes"
                )
            }

            Text(
                text = "View all message",
                color = colorResource(id = R.color.blue_2),
                modifier = Modifier
                    .clickable {
                        onViewMessage()
                    }
            )
        }
    }
}