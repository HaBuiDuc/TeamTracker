package com.buiducha.teamtracker.ui.screens.splash_screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalFoundationApi::class)
@Preview
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
            0 -> FirstSplashScreen()
            1 -> SecondSplashScreen()
            2 -> ThirdSpashScreen()
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
            .padding(90.dp)
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
                    .clip(CircleShape)
            )
        }
    }
}