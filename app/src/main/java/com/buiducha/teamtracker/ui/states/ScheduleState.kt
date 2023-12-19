package com.buiducha.teamtracker.ui.states

import com.buiducha.teamtracker.data.model.project.Board

data class ScheduleState(
    val boardList: List<Board> = emptyList()
)
