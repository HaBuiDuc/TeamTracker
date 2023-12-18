package com.buiducha.teamtracker.ui.screens.edit_workspace

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine

@Preview
@Composable
fun EditWorkspacePreview() {
    EditWorkspaceScreen(navController = rememberNavController())
}

@Composable
fun EditWorkspaceScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            EditWorkspaceTopBar(
                onPopBack = {
                    navController.popBackStack()
                },
                onEditSubmit = {

                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            EditInputField(
                label = "Workspace name",
                value = "",
                onValueChange = {}
            )
            EditInputField(
                label = stringResource(id = R.string.description),
                value = "",
                onValueChange = {}
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column {
                HorizontalLine()
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {

                        }
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(Color.Gray)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = stringResource(id = R.string.change_team_picture),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
                HorizontalLine()
            }
        }
    }
}

@Composable
private fun EditInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
//    Text(
//        text = label,
//        fontSize = 16.sp
//    )
    TextField(
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        label = {
            Text(
                text = label
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
    )

}