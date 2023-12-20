package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.BoardState
import com.buiducha.teamtracker.ui.states.PostsState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _boardState = MutableStateFlow(BoardState())
    val boardState: StateFlow<BoardState> = _boardState.asStateFlow()

    init {
        getBoard()
    }

    private fun getBoard() {
        TODO("Not yet implemented")
    }

}