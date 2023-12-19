package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.Board

data class BoardsState(
    val boardList: List<Board> = emptyList()
)
