package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.Board
import com.buiducha.teamtracker.data.model.project.Task

data class BoardState(
    val board: Board = Board(),
    val taskTitle: String = "",
    val taskList: List<Task> = emptyList()
)