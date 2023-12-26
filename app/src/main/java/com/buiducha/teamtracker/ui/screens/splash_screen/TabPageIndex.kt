package com.buiducha.teamtracker.ui.screens.splash_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R


@Preview(showSystemUi = true)
@Composable
fun TabPagePrev(){
    TabPageIndex(index = 2)
}




@Composable
fun TabPageIndex(index: Int) {
    Row{
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(50.dp)
                .height(if (index == 1) 10.dp else 8.dp)
                .clip(shape = CircleShape)
                .background(if (index == 1) colorResource(id = R.color.purple_700) else colorResource(id = R.color.lighter_blue))
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(50.dp)
                .height(if (index == 2) 10.dp else 8.dp)
                .clip(shape = CircleShape)
                .background(if (index == 2) colorResource(id = R.color.purple_700) else colorResource(id = R.color.lighter_blue))
        )
        Spacer(modifier = Modifier.size(10.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(50.dp)
                .height(if (index == 3) 10.dp else 8.dp)
                .clip(shape = CircleShape)
                .background(if (index == 3) colorResource(id = R.color.purple_700) else colorResource(id = R.color.lighter_blue))
        )
    }
}
