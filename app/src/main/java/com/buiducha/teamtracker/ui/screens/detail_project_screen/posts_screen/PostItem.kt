package com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.project.Posts
import java.util.Locale

@Composable
fun PostItem(navController: NavController,
             post: Posts) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp, 5.dp)
        .shadow(elevation = 5.dp, shape = RoundedCornerShape(15.dp)),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.white)))
    {
        Column(modifier = Modifier.padding(10.dp, 10.dp, 0.dp, 10.dp)) {
            Row(modifier = Modifier
                .fillMaxWidth()){
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
//                        text = workspace.name.substring(0, 2).uppercase(Locale.ROOT),
                        text = post.content.toString().substring(0, 2).uppercase(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Column(Modifier.weight(1f)) {
                    Text(modifier = Modifier
                        .height(29.dp)
                        .padding(start = 5.dp),
                        text = post.userId.toString(),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp)
                    Text(modifier = Modifier
                        .height(29.dp)
                        .padding(5.dp),
                        fontStyle = FontStyle.Italic,
                        text = post.timestamp.toString())
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.MoreVert,
                        contentDescription = "",
                        Modifier.clickable{true})
                }

            }

            Text(text = "Content content content content " +
                    "content content content content" +
                    " content content content content")

            Row() {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.ThumbUpOffAlt,
                        contentDescription = "")
                }
                Text(modifier = Modifier
                    .align(Alignment.CenterVertically),
                    fontStyle = FontStyle.Italic,
                    text = "100 likes")
            }


            Text(text = "View all message",
                color = colorResource(id = R.color.blue_2))
        }
    }
}