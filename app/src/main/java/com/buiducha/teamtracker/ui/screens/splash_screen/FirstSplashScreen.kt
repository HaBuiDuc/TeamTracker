package com.buiducha.teamtracker.ui.screens.splash_screen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.navigation.Screen








@Preview(showSystemUi = true)
@Composable
fun fssPreview(){
    FirstSplashScreen(navController = rememberNavController())
}








@Composable
fun FirstSplashScreen(navController: NavController){
    Box(modifier = Modifier
        .fillMaxSize())
    {
        Box(modifier = Modifier
            .fillMaxSize()){
            Row(modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp))
            {
                ClickableText(text = AnnotatedString("Skip"), onClick = {
                    navController.navigate(Screen.ThirdSplashScreen.route)
                },
                    modifier = Modifier.align(Alignment.CenterVertically))
                Icon(imageVector = Icons.Filled.ArrowForward, contentDescription = "")
            }

            Box(modifier = Modifier
                .align(Alignment.TopCenter)) {
                Image(painterResource(id = R.drawable.sls_1_img1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(400.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 80.dp))
                Box(
                    modifier = Modifier
                        .size(400.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 80.dp)
                        .background(color = colorResource(id = R.color.transparent_white))
                )
                Image(painterResource(id = R.drawable.splashscreen_img_1),
                    contentDescription = "",
                    modifier = Modifier
                        .size(300.dp)
                        .align(Alignment.TopCenter)
                        .offset(y = 80.dp))
            }

            Text(text = "WELCOME TO TEAMTRACKER",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = colorResource(id = R.color.textcl),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 280.dp))
            Text(text = "Bắt đầu quản lý dự án hiệu quả và tạo liên kết mạnh mẽ giữa các thành viên nhóm của bạn ngay bây giờ.",
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp,
                color = colorResource(id = R.color.textcl),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 180.dp, start = 10.dp, end = 10.dp))
        }

        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 80.dp)){
            TabPageIndex(index = 1)
        }

        Button(onClick = {
            navController.navigate(route = Screen.SecondSplashScreen.route)
        }, modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(bottom = 30.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Row() {
                Text(text = "Next", color = Color.Black, fontSize = 18.sp)
                Icon(imageVector = Icons.Filled.NavigateNext,
                    contentDescription = "", tint = Color.Black
                )
            }
        }
    }
}
