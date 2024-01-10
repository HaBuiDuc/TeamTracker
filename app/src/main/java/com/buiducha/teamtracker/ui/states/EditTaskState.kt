package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.user.UserData

data class EditTaskState(
    val title: String = "",
    val description: String = "",
    val tag: Int = 0,
    val startDate: Long? = null,
    val dueDate: Long? = null,
    val selectedUser: List<String> = emptyList(),
    val joinedMemberList: List<UserData> = emptyList(),
    val remainMemberList: List<UserData> = emptyList()
)