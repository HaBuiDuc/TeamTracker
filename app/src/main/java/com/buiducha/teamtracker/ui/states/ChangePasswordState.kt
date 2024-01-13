package com.buiducha.teamtracker.ui.states

data class ChangePasswordState(
    val oldPassword: String = "",
    val newPassword: String = "",
    val confirmPassword: String = ""
)
