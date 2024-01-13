package com.buiducha.teamtracker.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.ViewModel
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.data.model.project.WorkspaceMember
import com.buiducha.teamtracker.repository.FirebaseRepository
import com.buiducha.teamtracker.repository.StreamRepository
import com.buiducha.teamtracker.ui.states.HomeState
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    private val currentUserInfoViewModel: CurrentUserInfoViewModel
) : ViewModel() {
    private val firebaseRepository = FirebaseRepository.get()
    private val streamRepository = StreamRepository.get()
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

    fun startBarcodeScanner(
        activity: Activity
    ) {
        val integrator = IntentIntegrator(activity)
        integrator.setPrompt("Scan a QR code")
        integrator.setBeepEnabled(false)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()
    }

    fun startQRScanActivity(
        context: Activity,
        activityResultLauncher: ActivityResultLauncher<Intent>
    ) {
        val intentIntegrator = IntentIntegrator(context)
        intentIntegrator.setPrompt("Scan a QR code")
        intentIntegrator.setBeepEnabled(false)
        intentIntegrator.setOrientationLocked(false)
        val intent = intentIntegrator.createScanIntent()
        activityResultLauncher.launch(intent)
    }

    fun addMemberToWorkspace(
        workspaceId: String,
        onAddSuccess: () -> Unit
    ) {
        val workspaceMember = WorkspaceMember(
            workspaceId = workspaceId,
            userId = currentUserInfoViewModel.currentUserInfo.value.id
        )
        firebaseRepository.addMemberToWorkspace(
            workspaceMember = workspaceMember,
            onAddSuccess = onAddSuccess,
            onAddFailure = {

            }
        )
    }

    companion object {
        const val TAG = "HomeViewModel"
    }
}