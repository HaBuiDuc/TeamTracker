package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.Workspace

data class HomeState(
    val workspaceList: MutableList<Workspace> = mutableListOf()
)
