package com.buiducha.teamtracker.ui.screens.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabPageIndexSplashScreen(index: Int) {
    Row{
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(shape = CircleShape)
                .background(if (index == 1) Color.Gray else Color.White)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(shape = CircleShape)
                .background(if (index == 2) Color.Gray else Color.White)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(shape = CircleShape)
                .background(if (index == 3) Color.Gray else Color.White)
        )
    }
}