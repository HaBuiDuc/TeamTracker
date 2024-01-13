package com.buiducha.teamtracker.viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Task
import com.buiducha.teamtracker.data.model.project.TaskMember
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.EditTaskState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EditTaskViewModel(
    private val taskId: String,
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private lateinit var taskData: Task
    private val _editTaskState = MutableStateFlow(EditTaskState())
    val editTaskState: StateFlow<EditTaskState> = _editTaskState.asStateFlow()

    init {
        initValue()
    }

    fun updateTask(
        onUpdateSuccess: () -> Unit,
        onUpdateFailure: () -> Unit
    ) {
        val newTask = taskData.copy(
            title = editTaskState.value.title,
            description = editTaskState.value.description,
            tag = editTaskState.value.tag,
            startDate = editTaskState.value.startDate,
            dueDate = editTaskState.value.dueDate
        )

        firebaseRepository.updateTask(
            task = newTask,
            onUpdateSuccess = onUpdateSuccess,
            onUpdateFailure = onUpdateFailure
        )
    }

    private fun initValue() {
        firebaseRepository.getTask(
            taskId = taskId,
            onGetDataSuccess = { task ->
                taskData = task
                _editTaskState.value = _editTaskState.value.copy(
                    title = task.title,
                    description = task.description,
                    tag = task.tag,
                    startDate = task.startDate,
                    dueDate = task.dueDate
                )
            }
        )

        firebaseRepository.getTaskMember(
            taskId = taskId,
            onGetMemberSuccess = { memberList ->
                _editTaskState.value = _editTaskState.value.copy(
                    joinedMemberList = memberList
                )
                firebaseRepository.getWorkspaceMember(
                    workspaceId = selectedWorkspace.workspace.value.id,
                    onGetMemberSuccess = { userList ->
                        userList.removeIf { user -> memberList.contains(user) }
                        _editTaskState.value = _editTaskState.value.copy(
                            remainMemberList = userList
                        )
                    },
                    onGetMemberFailure = {}
                )
            },
            onGetMemberFailure = {

            }
        )


    }

    fun getTaskValue() {
        _editTaskState.value = _editTaskState.value.copy(

        )
    }

    fun removeMember(
        userId: String
    ) {
        val taskMember = TaskMember(
            taskId = taskId,
            userId = userId
        )
        firebaseRepository.removeMemberFromTask(
            taskMember = taskMember,
            onRemoveSuccess = {},
            onRemoveFailure = {}
        )
    }

    fun onAddMember() {
        _editTaskState.value.selectedUser.forEach { userId ->
            val taskMember = TaskMember(
                taskId = taskId,
                userId = userId
            )
            firebaseRepository.addMemberToTask(
                taskMember = taskMember,
                onAddSuccess = {},
                onAddFailure = {}
            )
        }
    }

    fun onSelectMember(memberId: String) {
//        if (editTaskState.value.selectedUser.contains(memberId)) {
//            val newList = editTaskState.value.selectedUser - memberId
//            _editTaskState.value = _editTaskState.value.copy(
//                selectedUser = newList
//            )
//        } else {
//            val newList = editTaskState.value.selectedUser + memberId
//            _editTaskState.value = _editTaskState.value.copy(
//                selectedUser = newList
//            )
//        }
        val newList = if (editTaskState.value.selectedUser.contains(memberId))
            editTaskState.value.selectedUser - memberId else
            editTaskState.value.selectedUser + memberId
        _editTaskState.value = _editTaskState.value.copy(
            selectedUser = newList
        )
    }

    fun setDueDate(date: Long) {
        _editTaskState.value = _editTaskState.value.copy(
            dueDate = date
        )
    }

    fun setStartDate(date: Long) {
        _editTaskState.value = _editTaskState.value.copy(
            startDate = date
        )
    }

    fun setTaskDes(des: String) {
        _editTaskState.value = _editTaskState.value.copy(
            description = des
        )
    }

    fun setTaskTitle(title: String) {
        _editTaskState.value = _editTaskState.value.copy(
            title = title
        )
    }
}