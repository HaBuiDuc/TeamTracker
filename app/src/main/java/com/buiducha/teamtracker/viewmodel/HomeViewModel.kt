package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.HomeState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        getWorkspace()
    }

    fun deleteWorkspace() {
        firebaseRepository.deleteWorkspace(
            workspaceId = homeState.value.selectedWorkspace?.id!!
        )
    }

    fun leaveWorkspace() {
        val workspaceMember = WorkspaceMember(
            userId = firebaseRepository.getCurrentUser()?.uid!!,
            workspaceId = homeState.value.selectedWorkspace?.id!!
        )

        firebaseRepository.removeMemberFromWorkspace(
            workspaceMember = workspaceMember,
            onRemoveSuccess = {},
            onRemoveFailure = {}
        )
    }

    private fun getWorkspace() {
        firebaseRepository.getWorkspaces(
            onGetWorkspaceSuccess = { workspaces ->
                _homeState.value = _homeState.value.copy(
                    workspaceList = workspaces
                )
            },
            onGetWorkspaceFailure = {

            }
        )
    }

    fun setSelectedWorkspace(workspace: Workspace) {
        _homeState.value = _homeState.value.copy(
            selectedWorkspace = workspace
        )
    }
}