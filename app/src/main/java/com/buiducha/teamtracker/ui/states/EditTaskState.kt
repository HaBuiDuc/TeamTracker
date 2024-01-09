package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.user.UserData

data class EditTaskState(
    val title: String = "",
    val description: String = "",
    val tag: Int = 0,
//    val selectedUser: List<UserData> = listOf(),
    val startDate: Long? = null,
    val dueDate: Long? = null,
    val memberList: List<UserData> = emptyList()
)