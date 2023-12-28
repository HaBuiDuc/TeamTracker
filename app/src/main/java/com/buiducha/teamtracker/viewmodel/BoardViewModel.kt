package com.buiducha.teamtracker.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Board
import com.buiducha.teamtracker.data.model.project.Task
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.BoardState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BoardViewModel(
//    private val selectedWorkspace: SelectedWorkspaceViewModel,
    private val board: Board
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _boardState = MutableStateFlow(BoardState(board = board))
    val boardState: StateFlow<BoardState> = _boardState.asStateFlow()

    init {
        Log.d(TAG, boardState.value.taskList.size.toString())
        Log.d(TAG, board.label)
        getTask()
    }

    private fun getTask() {
        firebaseRepository.getTasks(
            boardId = board.id,
            onGetTaskSuccess = {
                _boardState.value = _boardState.value.copy(
                    taskList = it
                )
            }
        )
    }

    fun setTaskTitle(title: String) {
        _boardState.value = _boardState.value.copy(
            taskTitle = title
        )
    }

    fun addTask() {
        if (boardState.value.taskTitle.isNotEmpty()) {
            val newTask = Task(
                boardId = boardState.value.board.id,
                title = boardState.value.taskTitle,
                authorId = firebaseRepository.getCurrentUser()!!.uid
            )

            firebaseRepository.addTask(
                task = newTask,
                onAddSuccess = {
                    setTaskTitle("")
                },
                onAddFailure = {

                }
            )
        }
    }
    companion object {
        const val TAG = "BoardViewModel"
    }
}