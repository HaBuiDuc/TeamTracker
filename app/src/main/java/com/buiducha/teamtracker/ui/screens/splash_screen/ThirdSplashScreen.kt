package com.buiducha.teamtracker.ui.screens.splash_screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
@Preview
@Composable
fun ThirdSplashScreen(){
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.super_light_blue))){


        Image(
            painterResource(id = R.drawable.team_tracker), contentDescription = "",
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.TopCenter)
                .padding(top = 30.dp))
        Text(text = "\nBắt đầu ngay",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 250.dp))
        Text(text = "Chúng ta sẽ tiến vào thế giới quản lý dự án cùng TeamTracker. Hãy đăng nhập hoặc đăng ký để bắt đầu hành trình quản lý dự án của bạn.\n",
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 400.dp, start = 10.dp, end = 10.dp))
        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 80.dp)){
            TabPageIndexSplashScreen(index = 3)
        }
    }
}


