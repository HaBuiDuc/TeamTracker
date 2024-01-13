package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.ScheduleState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ScheduleViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _scheduleState = MutableStateFlow(ScheduleState())
    val scheduleState: StateFlow<ScheduleState> = _scheduleState.asStateFlow()

    init {
        getBoards()
    }

    private fun getBoards() {
        firebaseRepository.getBoards(
            workspaceId = selectedWorkspace.workspace.value.id,
            onGetBoardsSuccess = { boards ->

//                viewModelScope.launch {
//                    val taskList = mutableListOf<Task>()
//                    boards.forEach { board ->
//                        val task = suspendCoroutine { continuation ->
//                            firebaseRepository.getUserInfo(
//                                userId = board.userId!!,
//                                onGetInfoSuccess = { user ->
//                                    continuation.resume(user)
//                                },
//                                onGetInfoFailure = {}
//                            )
//                            firebaseRepository.
//                        }
//                        taskList += task
//                    }
//                    Log.d(PostViewModel.TAG, "getTasks: ${taskList.size}")

                    _scheduleState.value = _scheduleState.value.copy(
                        boardList = boards
                    )
//                }
            },
            onGetBoardsFailure = {

            }
        )
    }

    fun deleteBoard(
        boardId: String
    ) {
        firebaseRepository.deleteBoard(
            boardId = boardId
        )
    }

}