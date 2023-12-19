package com.buiducha.teamtracker.ui.screens.settings.settings_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buiducha.teamtracker.R
import com.buiducha.teamtracker.data.model.setting.Settings
import com.buiducha.teamtracker.ui.navigation.Screen

@Composable
fun SettingList(navController: NavController) {
    Column {
        Settings.values().forEach { setting ->
            SettingItem(settingItem = setting, navController)
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
fun SettingItem(
    settingItem: Settings,
    navController: NavController
) {
    Row(
        modifier = Modifier.clickable {
            if(settingItem.settingName.equals(R.string.introduce)){
                navController.navigate(Screen.IntroduceScreen.route)
            }
        }
    ) {
        Icon(
            imageVector = settingItem.settingIcon,
            contentDescription = null,
            modifier = Modifier
                .size(26.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = stringResource(id = settingItem.settingName),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}