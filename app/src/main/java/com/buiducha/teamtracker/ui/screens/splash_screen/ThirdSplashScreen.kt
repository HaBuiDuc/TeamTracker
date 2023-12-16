package com.buiducha.teamtracker.ui.screens.splash_screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoubleArrow
import androidx.compose.material.icons.filled.NavigateBefore
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.navigation.Screen
@Composable
fun ThirdSplashScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize()
        .paint(
            painterResource(id = R.drawable.background_sps),
            contentScale = ContentScale.FillBounds
        ))
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painterResource(id = R.drawable.team_tracker_new_logo),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 80.dp)
            )
            Text(
                text = "Bắt đầu ngay",
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp,
                color = colorResource(id = R.color.textcl),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 300.dp)
            )
            Text(
                text = "Chúng ta sẽ tiến vào thế giới quản lý dự án cùng TeamTracker. Hãy đăng nhập hoặc đăng ký để bắt đầu hành trình quản lý dự án của bạn.",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = colorResource(id = R.color.textcl),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 180.dp, start = 10.dp, end = 10.dp)
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 80.dp)
            ) {
                TabPageIndex(index = 3)
            }
        }


        Button(onClick = {
            navController.navigate(route = Screen.SecondSplashScreen.route)
        }, modifier = Modifier
            .align(Alignment.BottomStart)
            .padding(bottom = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row() {
                Icon(imageVector = Icons.Filled.NavigateBefore,
                    contentDescription = "", tint = Color.Black
                )
                Text(text = "Prev", color = Color.Black, fontSize = 18.sp)
            }
        }


        Button(onClick = {


        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row() {
                Text(text = "Start", color = Color.Black, fontSize = 18.sp)
                Icon(imageVector = Icons.Filled.DoubleArrow,
                    contentDescription = "", tint = Color.Black
                )
            }
        }
    }
}
