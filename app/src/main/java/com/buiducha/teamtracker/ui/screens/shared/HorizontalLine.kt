package com.buiducha.teamtracker.ui.screens.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview()
@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
    width: Double = 0.8
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(width.dp)
            .background(color = color)
    )
}