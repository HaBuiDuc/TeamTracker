package com.buiducha.teamtracker.ui.screens.authentication.login_screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.theme.Blue40


@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginScreen(){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray), contentAlignment = Alignment.TopCenter
        ) {

            Image(painterResource(id = R.drawable.team_tracker_authentication),
                contentDescription = "",
                modifier = Modifier
                    .size(300.dp)
                    .padding(top = 100.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f)
                .background(Color.White)
                .padding(10.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Sign In",
                    fontSize = 30.sp,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                    )
                )
                Spacer(modifier = Modifier.padding(20.dp))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = { Text(text = "Email Address") },
                        placeholder = { Text(text = "Email Address") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.MailOutline,
                                contentDescription = ""
                            )
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        trailingIcon = {
                            if (password.isNotEmpty()) {
                                Icon(
                                    imageVector = if(isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable {
                                            isPasswordVisible = !isPasswordVisible
                                        }

                                )
                            }
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Filled.Lock,
                                contentDescription = ""
                            )
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        label = {
                            Text("Password")
                        },
                        placeholder = {
                            Text(text = "Password")
                        },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(0.8f),
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor =  Blue40,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(24),
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Sign In",
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.padding(20.dp))
                    Text(
                        text = "Create An Account",
                        fontSize = 16.sp,
                        modifier = Modifier
                            .clickable {

                            }
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                }
            }
        }
    }
}