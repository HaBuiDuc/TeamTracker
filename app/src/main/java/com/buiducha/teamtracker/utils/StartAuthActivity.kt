package com.buiducha.teamtracker.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.buiducha.teamtracker.activity.AuthActivity
import com.buiducha.teamtracker.activity.MainActivity

fun startAuthActivity(context: Context) {
    val intent = Intent(context, AuthActivity::class.java)
    (context as Activity).finish()
    context.startActivity(intent)
}