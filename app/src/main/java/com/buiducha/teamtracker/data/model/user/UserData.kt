package com.buiducha.teamtracker.data.model.user

import java.util.UUID

data class UserData(
    val id: String = UUID.randomUUID().mostSignificantBits.toString(),
    val fullName: String = "",
    val phoneNumber: String = "",
    val avatarUri: String? = null,
    val location: String = "",
    val company: String? = null,
    val dateOfBirth: String = ""
)
