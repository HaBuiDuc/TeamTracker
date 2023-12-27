package com.buiducha.teamtracker.viewmodel

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.ui.states.EditWorkspaceState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import java.util.UUID

class EditWorkspaceViewModel(
    private val selectedWorkspace: SelectedWorkspaceViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val _editWorkspaceState = MutableStateFlow(EditWorkspaceState())
    val editWorkspaceState: StateFlow<EditWorkspaceState> = _editWorkspaceState.asStateFlow()

    init {
        _editWorkspaceState.update {
            it.copy(workspace = selectedWorkspace.workspace.value)
        }
    }

    fun setWorkspaceDes(wsDes: String) {
        _editWorkspaceState.update {
            it.copy(workspace = it.workspace?.copy(describe = wsDes))
        }
    }

    fun setWorkspaceName(wsName: String) {
        _editWorkspaceState.update {
            it.copy(workspace = it.workspace?.copy(name = wsName))
        }
    }

    fun setWorkspaceAvatar(wsAvatar: String) {
        _editWorkspaceState.update {
            it.copy(workspace = it.workspace?.copy(avatar = wsAvatar))
        }
    }

    fun uploadImage(uri: Uri, context: Context, imgUrl: MutableState<String>, oldImageUrl: String) {
        var oldImage: String = ""
        if (oldImageUrl.length > 1){
            oldImage = oldImageUrl.substring(85, 125)
        }

        Toast.makeText(context,oldImage, Toast.LENGTH_SHORT).show()
        firebaseRepository.uploadImageToStorage(uri, context, imgUrl, oldImage)
        _editWorkspaceState.update {
            it.copy(workspace = it.workspace?.copy(avatar = imgUrl.value))
        }
    }

    fun editWorkspace(
        uri: Uri?,
        onUpdateSuccess: () -> Unit,
        onUpdateFailure: () -> Unit
    ) {
        val editedWorkspace = _editWorkspaceState.value.workspace ?: return
        firebaseRepository.updateWorkspace(
            uri = uri,
            workspace = editedWorkspace,
            onUpdateSuccess = onUpdateSuccess,
            onUpdateFailure = onUpdateFailure
        )
    }

    private fun isValueValid(workspace: Workspace?): Boolean {
        return workspace?.name?.isNotEmpty() ?: false
    }

}
