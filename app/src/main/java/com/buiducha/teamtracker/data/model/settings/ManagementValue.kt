package com.buiducha.teamtracker.data.model.settings

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.GroupRemove
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.buiducha.teamtracker.R

enum class ManagementValue(
    val icon: ImageVector,
    @StringRes val label: Int
) {
    WorkSpaceManagement(
        icon = Icons.Default.Settings,
        label = R.string.workspace_management
    ),
    CreateWorkspace(
        icon = Icons.Default.Add,
        label = R.string.create_workspace
    ),
    FindWorkspace(
        icon = Icons.Default.Groups,
        label = R.string.find_workspace
    ),
    JoinWorkspaceByQRCode(
        icon = Icons.Default.QrCodeScanner,
        label = R.string.join_workspace_by_qr_code
    ),

    ViewMembers(
        icon = Icons.Default.Group,
        label = R.string.view_members
    ),
    ManageMembers(
        icon = Icons.Default.Group,
        label = R.string.manage_members
    ),
    LeaveWorkspace(
        icon = Icons.Default.GroupRemove,
        label = R.string.leave_ws
    ),
    EditWorkspace(
        icon = Icons.Default.Edit,
        label = R.string.edit_ws
    ),
    DeleteWorkspace(
        icon = Icons.Default.Delete,
        label = R.string.delete_ws
    ),

    RemoveUser(
        icon = Icons.Default.Close,
        label = R.string.remove_user
    ),

    Delete(
        icon = Icons.Default.DeleteOutline,
        label = R.string.delete
    ),
    Copy(
        icon = Icons.Default.ContentCopy,
        label = R.string.copy
    ),
    AddTask(
        icon = Icons.Default.Add,
        label = R.string.add_task
    ),
    ScanQrCode(
        icon = Icons.Default.QrCode,
        label = R.string.qr_code
    )
}