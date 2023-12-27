package com.buiducha.teamtracker.ui.screens.settings.introduce_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.theme.Blue40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IntroduceScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Introduce") },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.ArrowBack, "")
                    }
                }
            )
        }
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .padding(top = 70.dp)) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "")
            Text(text = "TeamTracker",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.padding(5.dp))
            Text(text = "University of Information Technology",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Text(text = "Vietnam National University",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Text(text = "Develop applications on mobile devices",
                textAlign = TextAlign.Center)
            Text(text = "NT118.O12")

            Text(text = "Version: 1.0.0",
                fontWeight = FontWeight.Bold)
            Text(text = "License Details: None",
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.padding(20.dp))

            Column(horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Privacy & cookies",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Terms of use",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Third Party Software Notices and Information",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Connected experiences",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Diagnostic data Viewer",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }
}