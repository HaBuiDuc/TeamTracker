package com.buiducha.teamtracker.ui.screens.member_management.add_memeber_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.screens.shared.MemberItem
import com.buiducha.teamtracker.viewmodel.AddMemberViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel

@Composable
fun AddMemberScreen(
    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
    addMemberViewModel: AddMemberViewModel = viewModel {AddMemberViewModel(userInfoViewModel)},
) {
    val addMemberState by addMemberViewModel.addMemberState.collectAsState()
    Scaffold(
        topBar = {
            AddMemberTopBar(
                onCancel = {
                    navController.popBackStack()
                },
                onAddSubmit = {}
            )
        }
    ) {paddingValues ->
       Column(
           modifier = Modifier
               .padding(paddingValues)
       ) {
            TextField(
                value = addMemberState.query,
                onValueChange = {
                    addMemberViewModel.setQuery(it)
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                ),
                placeholder = {
                    Text(text = "Enter email address")
                },
                leadingIcon = {
                    Text(
                        text = "Add:",
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp
                            )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

           LazyColumn(
               modifier = Modifier
                   .padding(16.dp)
           ) {
               items(addMemberState.resultList) {userData ->
                   MemberItem(
                       member = userData,
                       modifier = Modifier
                           .padding(
                               vertical = 4.dp
                           )
                   ) {

                   }
               }

           }
       }
    }
}