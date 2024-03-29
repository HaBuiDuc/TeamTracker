package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.data.model.project.Workspace
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import java.util.Locale

@Composable
fun WorkspacesView(
    workspaceList: List<Workspace>,
    onMenuToggle: (Workspace) -> Unit,
    onSelectWorkspace: (Workspace) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(workspaceList) { workspace ->
            WorkspaceItem(
                workspace = workspace,
                onMenuToggle = onMenuToggle,
                onSelectWorkspace = onSelectWorkspace,
            )
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun WorkspaceItem(
    workspace: Workspace,
    modifier: Modifier = Modifier,
    onMenuToggle: (Workspace) -> Unit,
    onSelectWorkspace: (Workspace) -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .clickable {
                onSelectWorkspace(workspace)
            }
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (workspace.avatar == null) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(
                            color = Color.LightGray,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(12.dp)
                        .size(32.dp)
                ) {
                    Text(
                        text = workspace.name.substring(0, 2).uppercase(Locale.ROOT),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            } else {
                GlideImage(
                    model = workspace.avatar,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(10))
                        .width(54.dp)
                        .aspectRatio(1f)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = workspace.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                workspace.describe?.let { des ->
                    Text(
                        text = des,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Light,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                    )
                }

            }
        }
        IconButton(
            onClick = {
                onMenuToggle(workspace)
            }
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = null
            )
        }
    }
}