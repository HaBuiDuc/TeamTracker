package com.buiducha.teamtracker.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun convertDate(time: Long): String {
    val date = Date(time)
    return SimpleDateFormat("MMM dd yyyy HH:mm", Locale.getDefault()).format(date)
}