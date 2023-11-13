package com.buiducha.teamtracker.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.buiducha.teamtracker.MainActivity
import com.buiducha.teamtracker.viewmodel.LoginViewModel

fun startMainActivity(context: Context) {
//    Log.d(LoginViewModel.TAG, "startMainActivity: ")
    val intent = Intent(context, MainActivity::class.java)
    (context as Activity).finish()
    context.startActivity(intent)
}