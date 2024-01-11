package com.buiducha.teamtracker.viewmodel.workspace_viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.AddMemberState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddMemberViewModel(
    private val userInfoViewModel: UserInfoViewModel,
    private val selectedWorkspaceViewModel: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _addMemberState = MutableStateFlow(AddMemberState())
    val addMemberState: StateFlow<AddMemberState> = _addMemberState.asStateFlow()

    init {
        Log.d(TAG, userInfoViewModel.userInfo.value.toString())
    }

    fun addMembers() {
        addMemberState.value.selectedUser.forEach { userData ->
            val workspaceMember = WorkspaceMember(
                userId = userData.id,
                workspaceId = selectedWorkspaceViewModel.workspace.value.id
            )
            firebaseRepository.addMemberToWorkspace(
                workspaceMember = workspaceMember,
                onAddSuccess = {},
                onAddFailure = {}
            )
        }

    }

    fun addSelectedMember(user: UserData) {
        val newSelectedList = addMemberState.value.selectedUser + user
        val newList = addMemberState.value.resultList.toMutableList()
        newList.removeAll { newSelectedList.contains(it) }
        _addMemberState.value = _addMemberState.value.copy(
            selectedUser = newSelectedList,
            resultList = newList
        )
    }

    fun setQuery(query: String) {
        val newList = if (query.isEmpty()) {
            mutableListOf()
        } else {
            userInfoViewModel.userInfo.value.filter { user -> user.email.startsWith(query) }.toMutableList()
        }
        newList.removeAll { addMemberState.value.selectedUser.contains(it) }
        Log.d(TAG, addMemberState.value.selectedUser.size.toString())
        _addMemberState.value = _addMemberState.value.copy(
            query = query,
            resultList = newList
        )
    }
    companion object {
        const val TAG = "AddMemberViewModel"
    }
}