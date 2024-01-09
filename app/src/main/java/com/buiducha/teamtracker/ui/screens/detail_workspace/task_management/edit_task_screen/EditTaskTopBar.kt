package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.edit_task_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun EditTaskTopBar(
    onEditSubmit: () -> Unit,
    onPopBack: () -> Unit
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
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
                    imageVector = Icons.Default.Clear,
                    contentDescription = null
                )
            }
            Text(
                text = "Thẻ 1",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        IconButton(
            onClick = {
                onEditSubmit()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null
            )
        }
    }
}