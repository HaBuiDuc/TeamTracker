package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.Posts

data class DetailWorkspaceState (
    val postsList: MutableList<Posts> = mutableListOf(),
    val selectedPosts: Posts? = null
)