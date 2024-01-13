package com.buiducha.teamtracker.data.model.user

data class UserData(
    val id: String = "",
    val fullName: String = "",
    val phoneNumber: String = "",
    val avatarUri: String? = null,
    val location: String = "",
    val company: String? = null,
    val dateOfBirth: Long? = null,
    val email: String = "",
    val notiToken: String? = null
)
