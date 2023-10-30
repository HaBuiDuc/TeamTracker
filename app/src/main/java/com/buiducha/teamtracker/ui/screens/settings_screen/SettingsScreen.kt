package com.buiducha.teamtracker.ui.screens.settings_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.buiducha.teamtracker.ui.screens.shared.HorizontalLine

@Preview
@Composable
fun SettingsScreenPreview() {
    SettingsScreen()
}

@Composable
fun SettingsScreen() {
    Scaffold(
        topBar = {
            SettingsTopBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            ProfileSetting()
            HorizontalLine(
                modifier = Modifier
                    .padding(
                        top = 32.dp,
                        bottom = 24.dp
                    )
            )
            SettingList()
        }
    }
}