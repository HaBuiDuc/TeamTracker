package com.buiducha.teamtracker.ui.screens.splash_screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.navigation.Screen
import kotlinx.coroutines.delay
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AnimatedSplashScreen(navController: NavHostController){
    var startAnimation by remember {
        mutableStateOf(false)
    }
    var progress by remember {
        mutableStateOf(0f)
    }
    var alphaAnim = animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 4000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2200)
        navController.popBackStack()
        navController.navigate(Screen.LoginScreen.route)
    }
    // Animate the progress of the loading bar
    LaunchedEffect(key1 = startAnimation) {
        while (progress < 1f) {
            progress += 0.01f
            delay(2L)
        }
    }

    StartScreen(alpha = alphaAnim.value, progress = progress)
}


@Composable
fun StartScreen(alpha: Float, progress: Float) {
    var loadingText by remember { mutableStateOf("Loading") }

    LaunchedEffect(key1 = Unit) {
        while (true) {
            delay(500)  // delay 0.5 second
            loadingText = when (loadingText) {
                "Loading" -> "Loading."
                "Loading." -> "Loading.."
                "Loading.." -> "Loading..."
                else -> "Loading"
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.white))
                .fillMaxSize()
        ) {
            Image(
                painterResource(id = R.drawable.teamtracker2),
                contentDescription = "",
                modifier = Modifier
                    .size(400.dp)
                    .align(Alignment.Center)
                    .alpha(alpha = alpha)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()

                )
                Text(
                    text = loadingText,
                    color = Color.Blue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                )
            }
        }
    }
}
//@Preview(showSystemUi = true)
//@Composable
//fun xssPreview(){
//    StartScreen (alpha = 1f)
//}