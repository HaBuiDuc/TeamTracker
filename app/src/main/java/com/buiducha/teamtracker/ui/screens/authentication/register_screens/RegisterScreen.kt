package com.buiducha.teamtracker.ui.screens.authentication.register_screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.ui.theme.Blue40
import com.buiducha.teamtracker.viewmodel.RegisterViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
fun RegisterScreenPreview() {
    RegisterScreen(rememberNavController())
}


@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }


    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }


    val isPasswordMatch by remember {
        derivedStateOf { password == confirmPassword }
    }


    val valueNotEmpty by remember {
        derivedStateOf {
            password.isNotEmpty()
                    && email.isNotEmpty()
                    && confirmPassword.isNotEmpty()
        }
    }


    val context = LocalContext.current
    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) {padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Box(
                modifier = Modifier,
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painterResource(id = R.drawable.team_tracker_new_logo),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Sign Up",
                        fontSize = 30.sp,
                        color = Blue40,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(text = "Email Address") },
                            placeholder = { Text(text = "Email Address") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.MailOutline,
                                    contentDescription = ""
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )


                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(text = "Password") },
                            placeholder = { Text(text = "Password") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = ""
                                )
                            },
                            singleLine = true,
                            modifier = Modifier.fillMaxWidth(0.8f),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        isPasswordVisible = !isPasswordVisible
                                    },
                                    enabled = password.isNotEmpty()
                                ) {
                                    if (password.isNotEmpty()) {


                                        Icon(
                                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                        )
                                    }
                                }
                            },
                            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
                        )


                        OutlinedTextField(
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text(text = "Confirm Password") },
                            placeholder = { Text(text = "Confirm Password") },
                            singleLine = true,
                            isError = !isPasswordMatch,
                            supportingText = {
                                if (!isPasswordMatch) {
                                    Text(text = "Password is not match")
                                }
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Lock,
                                    contentDescription = ""
                                )
                            },
                            modifier = Modifier.fillMaxWidth(0.8f),
                            trailingIcon = {
                                IconButton(
                                    onClick = {
                                        isConfirmPasswordVisible = !isConfirmPasswordVisible
                                    },
                                    enabled = confirmPassword.isNotEmpty()
                                ) {
                                    if (confirmPassword.isNotEmpty()) {
                                        Icon(
                                            imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                                            contentDescription = "",
                                            modifier = Modifier
                                                .clip(CircleShape)
                                        )
                                    }
                                }
                            },
                            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None
                            else PasswordVisualTransformation()
                        )
                        Button(
                            onClick = {
                                if (registerViewModel.isValueValid(email, password, confirmPassword)) {
                                    registerViewModel.createUser(
                                        activity = activity,
                                        email = email,
                                        password = password,
                                        onCreateSuccess = {
                                            scope.launch {
                                                snackBarHostState.showSnackbar("Create account successfully")
                                                delay(2000)
                                                navController.navigate(Screen.LoginScreen.route)
                                            }
                                        },
                                        onCreateFailure = {
                                            scope.launch {
                                                snackBarHostState.showSnackbar("Create account failure")
                                            }
                                        }
                                    )
                                } else {
                                    scope.launch {
                                        snackBarHostState.showSnackbar("Email or password invalid")
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Blue40,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(24),
                        ) {
                            Text(
                                text = "Sign Up",
                                fontSize = 18.sp
                            )
                        }
                        Row {
                            Text(text = "Already have an account?",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(0.dp, 15.dp))
                            Text(
                                text = "Login now!",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(5.dp, 15.dp)
                                    .clickable {
                                        navController.popBackStack()
                                        navController.navigate(Screen.LoginScreen.route)
                                    },
                                color = Color.Blue
                            )
                        }

                    }
                }
                Image(
                    painterResource(id = R.drawable.login_image),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f)
                )
            }
        }
    }
}
