package com.buiducha.teamtracker.ui.screens.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R


@Composable
fun TabPageIndex(index: Int) {
    Row{
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(if (index == 1) 15.dp else 10.dp)
                .clip(shape = CircleShape)
                .background(if (index == 1) Color.Blue else colorResource(id = R.color.super_light_blue))
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(if (index == 2) 15.dp else 10.dp)
                .clip(shape = CircleShape)
                .background(if (index == 2) Color.Blue else colorResource(id = R.color.super_light_blue))
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(if (index == 3) 15.dp else 10.dp)
                .clip(shape = CircleShape)
                .background(if (index == 3) Color.Blue else colorResource(id = R.color.super_light_blue))
        )
    }
}
