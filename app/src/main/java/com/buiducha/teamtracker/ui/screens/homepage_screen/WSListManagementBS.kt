package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.data.model.settings.ManagementValue

@Composable
fun WSListManagementBS(
    onWSManagement: () -> Unit,
    onCreateWS: () -> Unit,
    onFindWS: () -> Unit,
    onJoinWSByQRCode: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                bottom = 16.dp
            )
    ) {
//        ManagementItem(
//            setting = ManagementValue.WorkSpaceManagement,
//            onOptionSelected = onWSManagement
//        )
        ManagementItem(
            setting = ManagementValue.CreateWorkspace,
            onOptionSelected = onCreateWS
        )
        ManagementItem(
            setting = ManagementValue.FindWorkspace,
            onOptionSelected = onFindWS
        )
        ManagementItem(
            setting = ManagementValue.JoinWorkspaceByQRCode,
            onOptionSelected = onJoinWSByQRCode
        )
    }
}