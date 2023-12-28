package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Board
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.CreateBoardState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateBoardViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
): ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _createBoardState = MutableStateFlow(CreateBoardState())
    val createBoardState: StateFlow<CreateBoardState> = _createBoardState.asStateFlow()

    fun setBoardLabel(label: String){
        _createBoardState.value = _createBoardState.value.copy(
            label = label
        )
    }

    fun createBoard(
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ){
        val newBoard = Board(
            label = createBoardState.value.label,
            workspaceId = selectedWorkspace.workspace.value.id
        )

        firebaseRepository.createBoard(
            board = newBoard,
            onCreateSuccess = {
                onCreateSuccess()
                setBoardLabel("")
            },
            onCreateFailure = onCreateFailure
        )
    }
}