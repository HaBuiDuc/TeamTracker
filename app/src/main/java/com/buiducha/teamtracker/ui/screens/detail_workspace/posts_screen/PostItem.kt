package com.buiducha.teamtracker.ui.screens.detail_workspace.posts_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.utils.convertDate

@Composable
fun PostItem(
    post: WorkspacePost,
    user: UserData,
    postOption: () -> Unit,
    onViewMessage: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                12.dp
            )
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                onViewMessage()
            }
    )
    {
        Column(
            modifier = Modifier
                .padding(
                    12.dp
                )
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row {
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
                            text = user.fullName.substring(0, 2).uppercase(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Top,
                        modifier = Modifier

                    ) {
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp),
                            text = user.fullName,
                            fontWeight = FontWeight.Medium,
                            fontSize = 16.sp
                        )
                        Text(
                            modifier = Modifier
                                .padding(5.dp),
                            fontStyle = FontStyle.Italic,
                            text = convertDate(post.timestamp!!)
                        )
                    }
                }
                IconButton(
                    onClick = {
                        postOption()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.MoreVert,
                        contentDescription = null,
                    )
                }

            }

            Column(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
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
                    text = "${post.likesCount} likes"
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