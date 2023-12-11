package com.buiducha.teamtracker.ui.screens.homepage_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buiducha.teamtracker.data.model.project.Workspace
import com.buiducha.teamtracker.ui.theme.DarkGreen

@Composable
fun WorkspacesView(
    workspaceList: List<Workspace>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        items(workspaceList) {workspace ->
            WorkspaceItem(workspace = workspace)
        }
    }
}

@Composable
private fun WorkspaceItem(
    workspace: Workspace,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { }
            .fillMaxWidth()
    ) {
       Box(
           modifier = Modifier
               .background(
                   color = Color.LightGray,
                   shape = RoundedCornerShape(16.dp)
               )
       ) {
           Icon(
               tint = DarkGreen,
               imageVector = Icons.Filled.Android,
               contentDescription = null,
               modifier = Modifier
                   .padding(12.dp)
                   .size(32.dp)
           )
       }
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = workspace.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
            workspace.describe?.let {des ->
                Text(
                    text = des,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Light
                )
            }

        }
    }
}