package com.buiducha.teamtracker.ui.screens.settings.settings_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine
import com.buiducha.teamtracker.utils.startAuthActivity
import com.buiducha.teamtracker.viewmodel.SettingsViewModel
import com.buiducha.teamtracker.viewmodel.shared_viewmodel.CurrentUserInfoViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    currentUserInfoViewModel: CurrentUserInfoViewModel,
    settingsViewModel: SettingsViewModel = viewModel()
) {
    val context = LocalContext.current
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            ProfileSetting(currentUserInfoViewModel = currentUserInfoViewModel)
            HorizontalLine(
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        bottom = 24.dp
                    )
            )
            SettingList(
                navController = navController,
                onLogout = {
                    settingsViewModel.logout()
                    startAuthActivity(context)
                }
            )
        }
    }
}