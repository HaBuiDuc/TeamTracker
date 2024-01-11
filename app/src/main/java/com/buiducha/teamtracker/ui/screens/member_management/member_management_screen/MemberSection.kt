package com.buiducha.teamtracker.ui.screens.member_management.member_management_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.data.model.user.UserData
import com.buiducha.teamtracker.ui.screens.shared.MemberItem

@Composable
fun MemberSection(
    memberList: List<UserData>,
    isWorkspaceOwner: Boolean,
    onMenuToggle: (UserData) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        LazyColumn {
            items(memberList) { member ->
                MemberItem(
                    member = member,
                    isWorkspaceOwner = isWorkspaceOwner,
                    onMenuToggle = { onMenuToggle(member) },
                    modifier = Modifier
                        .padding(
                            vertical = 4.dp
                        )
                )
            }
        }
    }
}
