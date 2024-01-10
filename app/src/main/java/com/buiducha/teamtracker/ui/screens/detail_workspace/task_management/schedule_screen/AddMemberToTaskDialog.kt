package com.buiducha.teamtracker.ui.screens.detail_workspace.task_management.schedule_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.data.model.user.UserData
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemberToTaskDialog(
    memberList: List<UserData>,
    selectedMember: List<String>,
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    onCheckChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
    ) {
        Column(
            modifier = modifier
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8)
                )
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            LazyColumn {
                items(memberList) {member ->
                    AddMemberItem(
                        member = member,
                        isChecked = (selectedMember.contains(member.id)),
                        onCheckChange = {
                            onCheckChange(member.id)
                        }
                    )
                }
            }
            TextButton(
                onClick = onConfirm,
                modifier = Modifier
                    .align(Alignment.End)
            ) {
                Text(
                    text = "Confirm",
                )
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AddMemberItem(
    member: UserData,
    isChecked: Boolean,
    onCheckChange: (Boolean) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 4.dp
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (member.avatarUri != null) {
                GlideImage(
                    model = member.avatarUri,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10))
                        .width(44.dp)
                        .aspectRatio(1f)
                )
            } else {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = Color.Blue,
                            shape = CircleShape
                        )
                        .padding(12.dp)
                ) {
                    Text(text = member.fullName.substring(0, 2).uppercase(Locale.ROOT))
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = member.fullName)
        }
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                onCheckChange(it)
            }
        )
    }
}