package com.buiducha.teamtracker.data.model

data class User(
    val id: String,
    val userName: String,
    val email: String,
    val phoneNumber: String,
    val avatarUri: String,
    val location: String,
    val company: String? = null
)
