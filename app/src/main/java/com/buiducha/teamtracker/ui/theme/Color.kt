package com.buiducha.teamtracker.ui.theme

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)
val Blue40 = Color(0xFF457B9D)

val PrimaryColor = Color(0xFF457B9D)

val DarkGreen = Color(0xFF7bc871)
val GrayTransparent = Color(0x80BEBEBE)
val LightGray = Color(0xFFf7f7f8)
val ShadeGray = Color(0xFFebedf4)

val TagColor1 = Color(0xFF9fc65b)
val TagColor2 = Color(0xFF81c1dd)
val TagColor3 = Color(0xFF689af8)
val TagColor4 = Color(0xFF9c90e9)
val TagColor5 = Color(0xFFe7796d)
val TagColor6 = Color(0xFFefce60)

@Composable
fun AuthenticTextFieldColor() = TextFieldDefaults.colors(
    focusedContainerColor = LightGray,
    unfocusedContainerColor = LightGray,
    disabledContainerColor = LightGray,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent
)