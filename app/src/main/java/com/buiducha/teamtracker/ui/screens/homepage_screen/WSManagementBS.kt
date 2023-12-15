package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.data.model.settings.ManagementValue

@Composable
fun WSManagementBS(
    onMemberManager: () -> Unit,
    onLeaveWorkspace: () -> Unit,
    onEditWorkspace: () -> Unit,
    onDeleteWorkspace: () -> Unit,
    isWorkspaceOwner: Boolean,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(
                bottom = 16.dp
            )
    ) {
        if (isWorkspaceOwner) {
            ManagementItem(
                setting = ManagementValue.ManageMembers,
                onOptionSelected = onMemberManager
            )
        } else {
            ManagementItem(
                setting = ManagementValue.ViewMembers,
                onOptionSelected = onMemberManager
            )
        }
        ManagementItem(
            setting = ManagementValue.LeaveWorkspace,
            onOptionSelected = onLeaveWorkspace
        )
        if (isWorkspaceOwner) {
            ManagementItem(
                setting = ManagementValue.EditWorkspace,
                onOptionSelected = onEditWorkspace
            )
            ManagementItem(
                setting = ManagementValue.DeleteWorkspace,
                onOptionSelected = onDeleteWorkspace
            )
        }

    }
}