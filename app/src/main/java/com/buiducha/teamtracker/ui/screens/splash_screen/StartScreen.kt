package com.buiducha.teamtracker.ui.screens.splash_screen

import android.util.Log
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.utils.startMainActivity
import com.buiducha.teamtracker.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun AnimatedSplashScreen(navController: NavHostController, splashViewModel: SplashViewModel = viewModel()) {
    var startAnimation by remember { mutableStateOf(false) }
    var progress by remember { mutableFloatStateOf(0f) }
    val context = LocalContext.current

    LaunchedEffect(key1 = true, block = {
        splashViewModel.checkAuthState(
            onLogged = {
                Log.d(TAG, "logged")
                splashViewModel.onLoginSuccess(
                    onUserExists = { startMainActivity(context = context) },
                    onUserNotExists = { navController.navigate(Screen.AddInfoScreen.route) }
                )
            },
            onNotLogged = {
                Log.d(TAG, "not logged")
                startAnimation = true
                navController.popBackStack()
                navController.navigate(Screen.LoginScreen.route)
            }
        )
    })

    LaunchedEffect(key1 = startAnimation) {
        while (progress < 1f) {
            progress += 0.01f
            delay(2L)
        }
    }

    StartScreen(alpha = 1f, progress = progress)
}

@Composable
fun StartScreen(alpha: Float, progress: Float) {
    var loadingText by remember { mutableStateOf("Loading") }
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2500, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

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
    ) {
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
                    .graphicsLayer(scaleX = scale, scaleY = scale) // Apply scale animation here
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

const val TAG = "StartScreen"