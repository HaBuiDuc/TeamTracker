package com.buiducha.teamtracker.ui.states

import java.util.Date

data class AddInfoState(
    val fullName: String = "",
    val phoneNumber: String = "",
    val dateOfBirth: String = "",
    val gender: Boolean = true,
    val location: String = "",
    val company: String = ""
)
