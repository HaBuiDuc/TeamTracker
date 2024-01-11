package com.buiducha.teamtracker.viewmodel.workspace_viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.MemberManagementState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MemberManagementViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _memberManagementState = MutableStateFlow(MemberManagementState())
    val memberManagementState: StateFlow<MemberManagementState> = _memberManagementState.asStateFlow()

    init {
        _memberManagementState.value = _memberManagementState.value.copy(
            isWorkspaceOwner = selectedWorkspace.workspace.value.workspaceOwnerId == firebaseRepository.getCurrentUser()?.uid
        )
        getMembers()
    }

    fun setSelectedMember(user: UserData) {
        _memberManagementState.value = _memberManagementState.value.copy(
            selectedMember = user
        )
    }

    fun removeMember() {
        val workspaceMember = WorkspaceMember(
            userId = memberManagementState.value.selectedMember.id,
            workspaceId = selectedWorkspace.workspace.value.id
        )

        firebaseRepository.removeMemberFromWorkspace(
            workspaceMember = workspaceMember,
            onRemoveSuccess = {},
            onRemoveFailure = {}
        )
    }

    private fun getMembers() {
        firebaseRepository.getWorkspaceMember(
            workspaceId = selectedWorkspace.workspace.value.id,
            onGetMemberSuccess = { userList ->
                val workspaceOwner = userList.find { user -> user.id == selectedWorkspace.workspace.value.workspaceOwnerId }
                workspaceOwner?.let {
                    userList.remove(workspaceOwner)
                }
                _memberManagementState.value = _memberManagementState.value.copy(
                    memberList = userList,
                    workspaceOwner = workspaceOwner!!
                )
            },
            onGetMemberFailure = {}
        )
    }
}