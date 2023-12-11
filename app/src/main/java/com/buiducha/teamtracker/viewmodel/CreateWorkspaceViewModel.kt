package com.buiducha.teamtracker.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.CreateWorkspaceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.time.LocalDate

class CreateWorkspaceViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _createWorkspaceState = MutableStateFlow(CreateWorkspaceState())
    val createWorkspaceState: StateFlow<CreateWorkspaceState> = _createWorkspaceState.asStateFlow()

    fun setWorkspaceDes(wsDes: String) {
        _createWorkspaceState.value = _createWorkspaceState.value.copy(
            workspaceDes = wsDes
        )
    }

    fun setWorkspaceName(wsName: String) {
        _createWorkspaceState.value = _createWorkspaceState.value.copy(
            workspaceName = wsName
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createWorkspace(
        onCreateSuccess: () -> Unit,
        onCreateFailure: () -> Unit
    ) {
        val workspace = Workspace(
            name = createWorkspaceState.value.workspaceName,
            describe = createWorkspaceState.value.workspaceDes,
            startDay = LocalDate.now().toString()
        )

       if(isValueValid()) {
           firebaseRepository.createWorkspace(
               workspace = workspace,
               onCreateSuccess = onCreateSuccess,
               onCreateFailure = onCreateFailure
           )
       }
    }

    private fun isValueValid(): Boolean  = (createWorkspaceState.value.workspaceName.isNotEmpty())
}