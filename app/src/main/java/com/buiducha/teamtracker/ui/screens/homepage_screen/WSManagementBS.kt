package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.data.model.WSManagement

@Composable
fun WSManagementBS(
    onWSManagement: () -> Unit,
    onCreateWS: () -> Unit,
    onFindWS: () -> Unit,
    onJoinWSById: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(
                bottom = 16.dp
            )
    ) {
        WSManagementItem(
            wsSetting = WSManagement.WorkSpaceManagement,
            onOptionSelected = onWSManagement
        )
        WSManagementItem(
            wsSetting = WSManagement.CreateWorkspace,
            onOptionSelected = onCreateWS
        )
        WSManagementItem(
            wsSetting = WSManagement.FindWorkspace,
            onOptionSelected = onFindWS
        )
        WSManagementItem(
            wsSetting = WSManagement.JoinWorkspaceById,
            onOptionSelected = onJoinWSById
        )
    }
}

@Composable
private fun WSManagementItem(
    wsSetting: WSManagement,
    onOptionSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clickable {
                onOptionSelected()
            }
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .fillMaxWidth()
    ){
        Icon(
            imageVector = wsSetting.icon,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = wsSetting.label),
            fontSize = 18.sp,
            fontWeight = FontWeight(400)
        )
    }
}