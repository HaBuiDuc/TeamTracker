package com.buiducha.teamtracker.ui.screens.detail_project_screen.posts_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.detail_project_screen.shared.DetailProjectTopBar
import com.buiducha.teamtracker.viewmodel.PostViewModel

@Composable
@Preview
fun PostScreenPreview(){
    PostsScreen(navController = rememberNavController(),
        postViewModel = PostViewModel())
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PostsScreen(
    navController: NavController,
    postViewModel: PostViewModel
){

    Scaffold(
        topBar = {
            DetailProjectTopBar(teamName = "team1")
        },
        floatingActionButton = {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier,
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.blue_2))
                ) {
                Row {
                    Icon(imageVector = Icons.Filled.AddCircleOutline,
                        contentDescription = "")
                    Text(
                        text = " Add new thread",
                        Modifier.align(Alignment.CenterVertically))
                }
            }

        },
        floatingActionButtonPosition = FabPosition.End
    ){
        Spacer(modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .background(color = Color.White))
        Column(modifier = Modifier
            .verticalScroll(rememberScrollState())
            .offset(y = 50.dp)
        ) {
        }
    }
}