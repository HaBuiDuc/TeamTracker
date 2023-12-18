package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.WorkspacePost

data class PostsState(
    val postList: List<WorkspacePost> = emptyList()
)