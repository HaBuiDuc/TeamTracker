package com.buiducha.teamtracker.ui.screens.member_management.member_management_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.navigation.Screen

@Preview
@Composable
fun MemberManagementTopBarPreview() {
//    MemberManagementTopBar {}
}

@Composable
fun MemberManagementTopBar(
    onMenuHide: () -> Unit,
    modifier: Modifier = Modifier,
    onPopBack: () -> Unit,
    navController: NavController
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                onMenuHide()
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    onPopBack()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
            Text(
                text = "Team member",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        IconButton(
            onClick = {
                navController.navigate(Screen.SearchMemberScreen.route)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        }
    }
}