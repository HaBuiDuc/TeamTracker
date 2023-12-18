package com.buiducha.teamtracker.ui.screens.edit_workspace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.R

@Composable
fun EditWorkspaceTopBar(
    onPopBack: () -> Unit,
    onEditSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
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
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(id = R.string.edit_ws),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
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