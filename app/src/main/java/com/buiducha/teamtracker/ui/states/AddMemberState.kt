package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.user.UserData

data class AddMemberState(
    val query: String = "",
    val resultList: List<UserData> = emptyList(),
    val selectedUser: List<UserData> = emptyList()
)
