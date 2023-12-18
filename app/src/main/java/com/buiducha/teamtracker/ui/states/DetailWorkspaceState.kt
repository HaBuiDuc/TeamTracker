package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.WorkspacePost

data class DetailWorkspaceState (
    val postsList: MutableList<WorkspacePost> = mutableListOf(),
    val selectedPosts: WorkspacePost? = null
)