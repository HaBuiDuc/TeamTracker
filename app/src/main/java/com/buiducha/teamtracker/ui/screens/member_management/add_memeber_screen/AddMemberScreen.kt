package com.buiducha.teamtracker.ui.screens.member_management.add_memeber_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine
import com.buiducha.teamtracker.ui.screens.shared.MemberItem
import com.buiducha.teamtracker.viewmodel.AddMemberViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.SelectedWorkspaceViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AddMemberScreen(
    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
    selectedWorkspaceViewModel: SelectedWorkspaceViewModel,
    addMemberViewModel: AddMemberViewModel = viewModel {
        AddMemberViewModel(
            userInfoViewModel = userInfoViewModel,
            selectedWorkspaceViewModel = selectedWorkspaceViewModel
        )
    },
) {
    val addMemberState by addMemberViewModel.addMemberState.collectAsState()
    Scaffold(
        topBar = {
            AddMemberTopBar(
                isAddEnabled = addMemberState.selectedUser.isNotEmpty(),
                onCancel = {
                    navController.popBackStack()
                },
                onAddSubmit = {
                    addMemberViewModel.addMembers()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
//            TextField(
//                value = addMemberState.query,
//                onValueChange = {
//                    addMemberViewModel.setQuery(it)
//                },
//                colors = TextFieldDefaults.colors(
//                    focusedContainerColor = Color.Transparent,
//                    unfocusedContainerColor = Color.Transparent
//                ),
//                placeholder = {
//                    Text(text = "Enter email address")
//                },
//                leadingIcon = {
//                    Text(
//                        text = "Add:",
//                        modifier = Modifier
//                            .padding(
//                                horizontal = 8.dp
//                            )
//                    )
//                },
//                modifier = Modifier
//                    .fillMaxWidth()
//            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(text = "Add: ")
                FlowRow(
                    modifier = Modifier
                        .wrapContentHeight()
                ) {
                    addMemberState.selectedUser.forEach { user ->
                        InputChip(
                            onClick = { },
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .clickable { }
                                )
                            },
                            selected = false,
                            label = {
                                Text(text = user.fullName)
                            },
                            colors = InputChipDefaults.inputChipColors(
                                containerColor = Color.Transparent,
                            ),
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .padding(
                                    horizontal = 2.dp
                                )
                        )
                    }
                    BasicTextField(
                        value = addMemberState.query,
                        onValueChange = { newText ->
                            addMemberViewModel.setQuery(newText)
                        },
                        textStyle = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.DarkGray
                        ),
                        decorationBox = { innerTextField ->
                            Box(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
                            ) {
                                if (addMemberState.query.isEmpty() && addMemberState.selectedUser.isEmpty()) {
                                    Text(
                                        text = "Enter email address.",
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Normal,
                                        color = Color.LightGray
                                    )
                                }
                                innerTextField()
                            }
                        },
                        modifier = Modifier
                            .weight(1f)
                    )
                }

            }

            HorizontalLine()

            LazyColumn(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                items(addMemberState.resultList) { userData ->
                    MemberItem(
                        member = userData,
                        onItemPressed = {
                            addMemberViewModel.addSelectedMember(userData)
                        },
                        modifier = Modifier
                            .padding(
                                vertical = 4.dp
                            )
                    )
                }

            }
        }
    }
}