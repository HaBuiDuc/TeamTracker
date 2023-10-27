package com.buiducha.teamtracker.ui.screens.splash_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope
@OptIn(ExperimentalFoundationApi::class)

@Composable
fun SplashScreen() {
    val pageState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val pageCount = 3 // Số trang bạn muốn
    var currentPage by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier.fillMaxSize(),
            pageCount = pageCount // Set pageCount here
        ) { page ->
            currentPage = page
        }

        when (currentPage) {
            0 -> FirstPage()
            1 -> SecondPage()
            2 -> ThirdPage()
        }

        SkipButton(
            onPageChange = { newPage ->
                currentPage = newPage
                coroutineScope.launch {
                    pageState.scrollToPage(newPage)
                }
            },
            pageState = pageState // Pass pageState here
        )

        PageIndicator(
            numPages = 3,
            currentPage = currentPage,
        )
    }
}

@Composable
fun FirstPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo và nội dung
        Logo()
        Text(
            text = "Welcome TeamTracker!",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = Color.White
        )
        Text(
            text = "Bắt đầu quản lý dự án hiệu quả và tạo liên kết mạnh mẽ giữa các thành viên nhóm của bạn ngay bây giờ.",
            style = TextStyle(
                fontSize = 18.sp,
                letterSpacing = 0.15.sp
            ),
            color = Color.White
        )
    }
}

@Composable
fun SecondPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo và nội dung
        Logo()
        Text(
            text = "TeamTracker  Quản lý dự án thông minh",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = Color.White
        )
        Text(
            text = "Tổ chức dự án, Giao tiếp dễ dàng, Theo dõi tiến độ",
            style = TextStyle(
                fontSize = 18.sp,
                letterSpacing = 0.15.sp
            ),
            color = Color.White
        )
    }
}

@Composable
fun ThirdPage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Logo và nội dung
        Logo()
        Text(
            text = "Bắt đầu ngay",
            style = TextStyle(
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = Color.White
        )
        Text(
            text = "Chúng ta sẽ tiến vào thế giới quản lý dự án cùng TeamTracker. Hãy đăng nhập hoặc đăng ký để bắt đầu hành trình quản lý dự án của bạn.",
            style = TextStyle(
                fontSize = 18.sp,
                letterSpacing = 0.15.sp
            ),
            color = Color.White
        )
    }
}

@Composable
fun Logo() {
    // Thay thế bằng hình ảnh logo của ứng dụng
    Image(
        painter = ColorPainter(Color.Blue),
        contentDescription = null,
        modifier = Modifier
            .size(100.dp)
            .padding(bottom = 16.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SkipButton(onPageChange: (Int) -> Unit, pageState: PagerState) { // Add PagerState parameter here
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        TextButton(
            onClick = {
                onPageChange(2)
                coroutineScope.launch {
                    pageState.scrollToPage(2)
                }
            },
            colors = ButtonDefaults.textButtonColors(contentColor = Color.White),
            modifier = Modifier
                .background(
                    color = Color.Transparent,
                    shape = MaterialTheme.shapes.small
                )
        ) {
            Text(text = "Skip")
        }
    }
}


@Composable
fun PageIndicator(numPages: Int, currentPage: Int) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        repeat(numPages) { pageIndex ->
            val color = if (pageIndex == currentPage) Color.Black else Color.Gray
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .background(color)
            )
        }
    }
}