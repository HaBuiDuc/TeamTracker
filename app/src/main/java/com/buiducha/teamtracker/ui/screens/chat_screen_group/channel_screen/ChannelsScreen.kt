package com.buiducha.teamtracker.ui.screens.chat_screen_group.channel_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.repository.StreamRepository
import com.buiducha.teamtracker.ui.navigation.Screen
import com.buiducha.teamtracker.ui.theme.LightGray
import com.buiducha.teamtracker.viewmodel.chat_viewmodel.ChannelViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel
import io.getstream.chat.android.compose.ui.channels.ChannelsScreen
import io.getstream.chat.android.compose.ui.channels.list.ChannelList
import io.getstream.chat.android.compose.ui.theme.ChatTheme
import io.getstream.chat.android.models.InitializationState

@Composable
fun ChannelsScreen(
    navController: NavController,
    currentUserInfoViewModel: CurrentUserInfoViewModel,
    channelViewModel: ChannelViewModel = viewModel { ChannelViewModel(currentUserInfoViewModel) }
) {
    val clientInitialisationState by channelViewModel.clientInitState().collectAsState()
    val currentUserInfo by currentUserInfoViewModel.currentUserInfo.collectAsState()

    ChatTheme {
        Scaffold(
            topBar = {
                ChannelsTopBar(
                    userData = currentUserInfo
                )
            },
            modifier = Modifier
                .background(
                    color = LightGray
                )
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .background(
                        color = LightGray
                    )
                    .padding(paddingValues)
            ) {
                ChannelSearchBar {
                    navController.navigate(Screen.UserSearchScreen.route)
                }
                when (clientInitialisationState) {
                    InitializationState.COMPLETE -> {
                        ChannelsScreen(
                            title = stringResource(id = R.string.app_name),
                            isShowingSearch = false,
                            isShowingHeader = false,
                            onItemClick = { channel ->
                                val route = "${Screen.ChatScreen.route}/${channel.cid}"
                                navController.navigate(route)
                            },
                            onBackPressed = { }
                        )
                    }

                    InitializationState.INITIALIZING -> {
                        Text(text = "Initialising...")
                    }

                    InitializationState.NOT_INITIALIZED -> {
                        Text(text = "Not initialized...")
                        channelViewModel.userInit()
                    }

                    else -> {}
                }
            }
        }
    }
}