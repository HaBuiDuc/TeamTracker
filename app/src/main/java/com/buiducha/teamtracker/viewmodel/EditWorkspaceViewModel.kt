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

    companion object {
        fun uploadImageToStorage(uri: Uri, context: Context, imgUrl: MutableState<String>) {
            val storage = Firebase.storage
            var storageRef = storage.reference
            val uniqueImageName: UUID? = UUID.randomUUID()
            var spaceRef: StorageReference = storageRef.child("images/$uniqueImageName.jpg")

            val byteArray: ByteArray? = context.contentResolver
                .openInputStream(uri)
                ?.use { it.readBytes() }

            byteArray?.let {

                var uploadTask = spaceRef.putBytes(byteArray)
                uploadTask.addOnFailureListener {
                    Toast.makeText(context,"upload failed", Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    imgUrl.value = it.metadata?.path.toString()
                }
            }
        }
    }

    fun editWorkspace(
        onUpdateSuccess: () -> Unit,
        onUpdateFailure: () -> Unit
    ) {
        val editedWorkspace = editWorkspaceState.value.workspace ?: return
        firebaseRepository.updateWorkspace(
            workspace = editedWorkspace,
            onUpdateSuccess = onUpdateSuccess,
            onUpdateFailure = onUpdateFailure
        )
    }

    private fun isValueValid(workspace: Workspace?): Boolean {
        return workspace?.name?.isNotEmpty() ?: false
    }

}