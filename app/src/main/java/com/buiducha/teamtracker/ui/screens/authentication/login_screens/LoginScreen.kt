package com.buiducha.teamtracker.ui.screens.authentication.login_screens

import android.app.Activity
import android.util.Log
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
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.buiducha.teamtracker.utils.startMainActivity
import com.buiducha.teamtracker.viewmodel.auth_viewmodel.LoginViewModel
import kotlinx.coroutines.launch

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navController = rememberNavController())
}


@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }


    var isPasswordVisible by remember {
        mutableStateOf(false)
    }


    val activity = LocalContext.current as Activity
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
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
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(10.dp))
                    Text(
                        text = "Sign In",
                        fontSize = 30.sp,
                        color = Blue40,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    Spacer(modifier = Modifier.padding(10.dp))
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
                                        imageVector = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
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

                        TextButton(
                            onClick = {
                                navController.navigate(Screen.ForgotPasswordScreen.route)
                            },
                            modifier = Modifier
                                .align(Alignment.End)
                        ) {
                            Text(
                                text = stringResource(id = R.string.forgot_password),
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            )
                        }

                        Button(
                            onClick = {
                                if (loginViewModel.isValueValid(email, password)) {
                                    loginViewModel.userLogin(
                                        activity = activity,
                                        email = email,
                                        password = password,
                                        onLoginSuccess = {
                                            Log.d(TAG, "LoginScreen: onLoginSuccess")
                                            loginViewModel.onLoginSuccess(
                                                onUserExists = {
                                                    Log.d(TAG, "LoginScreen: onLoginSuccess")
                                                    startMainActivity(context = context)
                                                },
                                                onUserNotExists = {
                                                    Log.d(TAG, "LoginScreen: onLoginFailure")
                                                    navController.navigate(Screen.AddInfoScreen.route)
                                                }
                                            )
                                        },
                                        onLoginFailure = { msg ->
                                            scope.launch {
                                                snackBarHostState.showSnackbar(msg)
                                            }
                                        }
                                    )
                                } else {
                                    scope.launch {
                                        snackBarHostState.showSnackbar("Email or password invalid")
                                    }
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Blue40,
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
                        Row {
                            Text(
                                text = "Don't have an account?",
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(0.dp, 15.dp)
                            )
                            Text(
                                text = "Sign up here!",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(5.dp, 15.dp)
                                    .clickable {
                                        navController.popBackStack()
                                        navController.navigate(Screen.RegisterScreen.route)
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
                        .fillMaxHeight(0.6f)
                )
            }
        }
    }
}


const val TAG = "LoginScreen"
