package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.user.UserData

data class MemberManagementState(
    val memberList: MutableList<UserData> = mutableListOf(),
    val workspaceOwner: UserData = UserData(),
    val isWorkspaceOwner: Boolean = false
)
