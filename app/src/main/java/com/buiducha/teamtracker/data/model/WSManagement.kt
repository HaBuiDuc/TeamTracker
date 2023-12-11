package com.buiducha.teamtracker.data.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tag
import androidx.compose.ui.graphics.vector.ImageVector
import com.buiducha.teamtracker.R

enum class WSManagement(
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
    JoinWorkspaceById(
        icon = Icons.Default.Tag,
        label = R.string.join_workspace_by_id
    )
}