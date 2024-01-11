package com.buiducha.teamtracker.ui.screens.chat_screen_group.user_search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.screens.shared.MemberItem
import com.buiducha.teamtracker.viewmodel.chat_viewmodel.UserSearchViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.UserInfoViewModel

@Composable
fun UserSearchScreen(
    navController: NavController,
    userInfoViewModel: UserInfoViewModel,
    currentUserInfoViewModel: CurrentUserInfoViewModel,
    userSearchViewModel: UserSearchViewModel = viewModel {
        UserSearchViewModel(
            userInfo = userInfoViewModel,
            currentUserInfo = currentUserInfoViewModel
        )
    }
) {
    val userSearchState by userSearchViewModel.userSearchState.collectAsState()
    Scaffold(
        topBar = {
            UserSearchTopBar(
                query = userSearchState.query,
                onQuery = {
                    userSearchViewModel.setQuery(it)
                },
                onPopBack = {
                    navController.popBackStack()
                }
            )
        },
        modifier = Modifier
            .padding(8.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn {
                items(userSearchState.resultList) { user ->
                    MemberItem(
                        member = user,
                        onItemPressed = {
                            userSearchViewModel.createChannel(user.id)
                        }
                    )
                }
            }
        }
    }
}