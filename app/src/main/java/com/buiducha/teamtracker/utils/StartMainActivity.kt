package com.buiducha.teamtracker.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.buiducha.teamtracker.MainActivity

fun startMainActivity(context: Context) {
    val intent = Intent(context, MainActivity::class.java)
    (context as Activity).finish()
    context.startActivity(intent)
}