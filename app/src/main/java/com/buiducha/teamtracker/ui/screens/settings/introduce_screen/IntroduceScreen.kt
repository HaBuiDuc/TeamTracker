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
            Text(text = "Trường Đại học Công nghệ Thông tin",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Text(text = "Đại học Quốc gia Hồ Chí Minh",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            Text(text = "Phát triển ứng dụng trên thiết bị di động",
                textAlign = TextAlign.Center)
            Text(text = "Lớp NT118.O12")

            Text(text = "Phiên bản: 1.0.0",
                fontWeight = FontWeight.Bold)
            Text(text = "Chi tiết giấy phép: không có",
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.padding(20.dp))

            Column(horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()) {
                Text(text = "Quyền riêng tư & cookie",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Điều khoản sử dụng",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Thông tin và thông báo về phần mềm của bên thứ ba",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Trải nghiệm được kết nối",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
                Text(text = "Trình xem dữ liệu chẩn đoán",
                    color = Blue40,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }
}