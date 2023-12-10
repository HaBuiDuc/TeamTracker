package com.buiducha.teamtracker.ui.screens.splash_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.R


@Preview(showSystemUi = true)
@Composable
fun SplashScreen() {
    var currentScreen by remember { mutableStateOf(1) }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = colorResource(id = R.color.super_light_blue))){
        when (currentScreen) {
            1 -> FirstSplashScreen()
            2 -> SecondSplashScreen()
            3 -> ThirdSplashScreen()
        }
        if(currentScreen != 3){
            Text(text = "Skip", color = Color.White, modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(20.dp)
                .clickable(true,  onClick = {
                    currentScreen = 3
                }))
        }


        Box(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 80.dp)){
            TabPageIndexSplashScreen(index = currentScreen)
        }
        if(currentScreen != 1){
            Button(onClick = {
                currentScreen -= 1
            }, modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(bottom = 30.dp, start = 20.dp)) {
                Text(text = "Prev")
            }
        }

        if(currentScreen != 3){
            Button(onClick = {
                currentScreen += 1
            }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 30.dp, end = 20.dp)) {
                Text(text = "Next")
            }
        }

        if(currentScreen==3){
            Button(onClick = { /*TODO*/ }, modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 30.dp, end = 20.dp)) {
                Text(text = "Start")
            }
        }
    }
}
