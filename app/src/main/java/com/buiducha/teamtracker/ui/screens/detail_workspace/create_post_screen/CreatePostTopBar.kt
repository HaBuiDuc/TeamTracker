package com.buiducha.teamtracker.ui.screens.detail_workspace.create_post_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Send
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
fun CreatePostTopBar(
    onCreateSubmit: () -> Unit,
    onPopBack: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
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
                text = stringResource(id = R.string.new_post),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
        IconButton(
            onClick = {
                onCreateSubmit()
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.Send,
                contentDescription = null
            )
        }
    }
}