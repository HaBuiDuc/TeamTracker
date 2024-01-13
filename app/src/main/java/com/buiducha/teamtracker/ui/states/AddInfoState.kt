package com.buiducha.teamtracker.ui.states

data class AddInfoState(
    val fullName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: Long? = null,
    val gender: Boolean = true,
    val location: String = "",
    val company: String = ""
)
