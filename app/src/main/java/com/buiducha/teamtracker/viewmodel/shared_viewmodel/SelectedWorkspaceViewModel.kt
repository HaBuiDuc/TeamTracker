package com.buiducha.teamtracker.viewmodel.shared_viewmodel

import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.repository.FirebaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SelectedWorkspaceViewModel : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _workspace = MutableStateFlow(Workspace())
    val workspace: StateFlow<Workspace> = _workspace.asStateFlow()

    fun workspaceUpdate(workspace: Workspace) {
        _workspace.value = workspace
    }
}