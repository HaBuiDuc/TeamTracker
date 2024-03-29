package com.buiducha.teamtracker.ui.screens.member_management.member_management_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.data.model.settings.ManagementValue
import com.buiducha.teamtracker.ui.screens.homepage_screen.ManagementItem

@Composable
fun MemberManagementBS(
    onRemoveUser: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(
                bottom = 16.dp
            )
    ) {
        ManagementItem(
            setting = ManagementValue.RemoveUser,
            onOptionSelected = onRemoveUser
        )
    }
}