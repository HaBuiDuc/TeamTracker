package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.WorkspacePost
import com.buiducha.teamtracker.data.model.user.UserData

data class PostsState(
    val postList: List<WorkspacePost> = emptyList(),
    val userList: List<UserData> = emptyList()
)