package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.user.UserData

data class UserSearchState(
    val query: String = "",
    val resultList: List<UserData> = emptyList()
)
